package com.example.calendarThriftServer;

import com.example.calendarThriftServer.model.EmployeeModel;
import com.example.calendarThriftServer.model.MeetingRoomModel;
import com.example.calendarThriftServer.model.MeetingStatusModel;
import com.example.calendarThriftServer.repository.EmployeeRepo;
import com.example.thriftMeeting.*;
import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class MeetingHandler implements IMeetingService.Iface {

    @Autowired
    private EmployeeRepo employeeRepo;

    private final EntityManager entityManager;

    public MeetingHandler(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    public boolean canScheduleMeeting(IMeetingServiceDTO meetingDTO) throws TException {

        // check missing input
        if( meetingDTO.getEmployeeIDs().size()==0 || meetingDTO.getStartTime()==null || meetingDTO.getEndTime()==null ){
            throw new TException(" Missing employeeIDs or startTime or endTime ");
        }

        // number of employee if less 6
        if(meetingDTO.getEmployeeIDs().size()<6){
            throw new TException("Number of Employee is less than 6");
        }

        // Parse startTime and endTime as LocalDateTime
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime start = LocalDateTime.parse(meetingDTO.getStartTime(), formatter);
        LocalDateTime end = LocalDateTime.parse(meetingDTO.getEndTime(), formatter);

        // Get the LocalTime part from start and end time
        LocalTime startTime = start.toLocalTime();
        LocalTime endTime = end.toLocalTime();

        // Check if startTime and endTime are between 10 AM and 6 PM
        LocalTime tenAM = LocalTime.of(10, 0);
        LocalTime sixPM = LocalTime.of(18, 0);

        if (startTime.isBefore(tenAM) || startTime.isAfter(sixPM)) {
            throw new InvalidFieldException("Start time must be between 10 AM and 6 PM.");
        }

        if (endTime.isBefore(tenAM) || endTime.isAfter(sixPM)) {
            throw new InvalidFieldException("End time must be between 10 AM and 6 PM.");
        }

        // Check if the difference between startTime and endTime is at least 30 minutes
        Duration duration = Duration.between(start, end);
        if (duration.toMinutes() < 30) {
            throw new InvalidFieldException("The meeting duration must be at least 30 minutes.");
        }

        // Check if all employees are free during the meeting time
        for (int employeeId : meetingDTO.employeeIDs) {
            Query employeeMeetingQuery = entityManager.createQuery(
                    "SELECT ms FROM MeetingStatusModel ms " +
                            "WHERE ms.meeting.meetingTime BETWEEN :start AND :end " +
                            "AND :employeeId MEMBER OF ms.employees"
            );
            employeeMeetingQuery.setParameter("start", start);
            employeeMeetingQuery.setParameter("end", end);
            employeeMeetingQuery.setParameter("employeeId", employeeId);

            List<MeetingStatusModel> employeeMeetings = employeeMeetingQuery.getResultList();
            if (!employeeMeetings.isEmpty()) {
                throw new NotFoundException("Employee with ID " + employeeId + " is already booked during the selected time.");
            }
        }

        // Count employees per office
        Map<Integer, Integer> officeEmployeeCount = new HashMap<>();
        for (int employeeId : meetingDTO.getEmployeeIDs()) {
            EmployeeModel employee = employeeRepo.findById(employeeId).orElseThrow(() -> new NotFoundException("Employee with ID " + employeeId + " not found."));
            int officeId = employee.getOffice().getOfficeId();
            officeEmployeeCount.put(officeId, officeEmployeeCount.getOrDefault(officeId, 0) + 1);
        }

        // Sort the offices by the number of employees in descending order
        List<Map.Entry<Integer, Integer>> sortedOffices = new ArrayList<>(officeEmployeeCount.entrySet());
        sortedOffices.sort((a, b) -> b.getValue().compareTo(a.getValue()));


        for (Map.Entry<Integer, Integer> officeEntry : sortedOffices) {
            int officeId = officeEntry.getKey();

            // Check for meeting room availability in this office
            Query roomAvailabilityQuery = entityManager.createQuery(
                    "SELECT mr FROM MeetingRoomModel mr " +
                            "WHERE mr.office.officeId = :officeId " +
                            "AND mr.isEnable = true " +
                            "AND NOT EXISTS (SELECT m FROM MeetingModel m " +
                            "WHERE m.meetingRoom.roomId = mr.roomId " +
                            "AND m.meetingTime BETWEEN :start AND :end)"
            );
            roomAvailabilityQuery.setParameter("officeId", officeId);
            roomAvailabilityQuery.setParameter("start", start);
            roomAvailabilityQuery.setParameter("end", end);

            List<MeetingRoomModel> availableRooms = roomAvailabilityQuery.getResultList();

            if (!availableRooms.isEmpty()) {
                return true;
            }
        }

        throw new NotFoundException("No available meeting room for the selected time.");

    }
}
