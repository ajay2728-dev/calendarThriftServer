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
import java.util.*;


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


        // Parse startTime and endTime as LocalDateTime
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime start = LocalDateTime.parse(meetingDTO.getStartTime(), formatter);
        LocalDateTime end = LocalDateTime.parse(meetingDTO.getEndTime(), formatter);

        // Check if all employees are free during the meeting time
        for (int employeeId : meetingDTO.employeeIDs) {
            Optional<EmployeeModel> employeeOpt = employeeRepo.findById(employeeId);
            if(!employeeOpt.isPresent()){
                throw new TException("Employee with "+ employeeId + "not found");
            }

            EmployeeModel employee = employeeOpt.get();

            Query employeeMeetingQuery = entityManager.createQuery(
                    "SELECT ms FROM MeetingStatusModel ms " +
                            "WHERE ms.meeting.meetingTime BETWEEN :start AND :end " +
                            "AND :employee MEMBER OF ms.employees"
            );
            employeeMeetingQuery.setParameter("start", start);
            employeeMeetingQuery.setParameter("end", end);
            employeeMeetingQuery.setParameter("employee", employee);

            List<MeetingStatusModel> employeeMeetings = employeeMeetingQuery.getResultList();
            if (!employeeMeetings.isEmpty()) {
                throw new TException("Employee with ID " + employeeId + " is already booked during the selected time.");
            }
        }

        // Count employees per office
        Map<Integer, Integer> officeEmployeeCount = new HashMap<>();
        for (int employeeId : meetingDTO.getEmployeeIDs()) {
            EmployeeModel employee = employeeRepo.findById(employeeId).orElseThrow(() -> new TException("Employee with ID " + employeeId + " not found."));
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

        throw new TException("No available meeting room for the selected time.");

    }
}
