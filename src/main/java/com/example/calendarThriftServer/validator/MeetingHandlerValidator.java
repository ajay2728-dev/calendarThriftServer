package com.example.calendarThriftServer.validator;

import com.example.calendarThriftServer.model.EmployeeModel;
import com.example.calendarThriftServer.model.MeetingRoomModel;
import com.example.calendarThriftServer.model.MeetingStatusModel;
import com.example.calendarThriftServer.repository.EmployeeRepo;
import com.example.calendarThriftServer.repository.MeetingRoomRepo;
import com.example.calendarThriftServer.repository.MeetingStatusRepo;
import com.example.thriftMeeting.IMeetingServiceDTO;
import com.example.thriftMeeting.IMeetingServiceResponse;
import com.example.thriftMeeting.MeetingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Component
public class MeetingHandlerValidator {

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private MeetingStatusRepo meetingStatusRepo;

    @Autowired
    private MeetingRoomRepo meetingRoomRepo;


    public void checkEmployeeMeetingConflict(IMeetingServiceDTO meetingDTO, LocalDateTime start, LocalDateTime end) throws MeetingException {
        // Check if all employees are free during the meeting time
        for (int employeeId : meetingDTO.getEmployeeIDs()) {
            Optional<EmployeeModel> employeeOpt = employeeRepo.findById(employeeId);
            if (!employeeOpt.isPresent()) {
                throw new MeetingException("Employee not found with given employeeId: " + employeeId, 404);
            }

            List<MeetingStatusModel> employeeMeetings = meetingStatusRepo.findMeetingsByEmployeeAndTimeRange(employeeId,start,end);
            if (!employeeMeetings.isEmpty()) {
                throw new MeetingException("Employee with ID " + employeeId + " is already booked during the selected time.", 409);
            }
        }
    }

    public MeetingRoomModel checkAvailableRoom(IMeetingServiceDTO meetingDTO, LocalDateTime start, LocalDateTime end) {

        // Count employees per office
        Map<Integer, Integer> officeEmployeeCount = new HashMap<>();
        for (int employeeId : meetingDTO.getEmployeeIDs()) {
            Optional<EmployeeModel> employeeOpt = employeeRepo.findById(employeeId);
            int officeId = employeeOpt.get().getOffice().getOfficeId();
            officeEmployeeCount.put(officeId, officeEmployeeCount.getOrDefault(officeId, 0) + 1);
        }

        // Sort the offices by the number of employees in descending order
        List<Map.Entry<Integer, Integer>> sortedOffices = new ArrayList<>(officeEmployeeCount.entrySet());
        sortedOffices.sort((a, b) -> b.getValue().compareTo(a.getValue()));

        for (Map.Entry<Integer, Integer> officeEntry : sortedOffices) {
            int officeId = officeEntry.getKey();

            List<MeetingRoomModel> availableRooms = meetingRoomRepo.findAvailableMeetingRooms(officeId,start,end);

            if (!availableRooms.isEmpty()) {
                return availableRooms.get(0);
            }
        }
        return null;
    }
}
