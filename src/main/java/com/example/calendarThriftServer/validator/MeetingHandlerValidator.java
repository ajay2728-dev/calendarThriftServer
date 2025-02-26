package com.example.calendarThriftServer.validator;

import com.example.calendarThriftServer.model.EmployeeModel;
import com.example.calendarThriftServer.model.MeetingRoomModel;
import com.example.calendarThriftServer.model.EmployeeMeetingStatusModel;
import com.example.calendarThriftServer.repository.EmployeeRepo;
import com.example.calendarThriftServer.repository.MeetingRoomRepo;
import com.example.calendarThriftServer.repository.EmployeeMeetingStatusRepo;
import com.example.thriftMeeting.IMeetingServiceDTO;
import com.example.thriftMeeting.MeetingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class MeetingHandlerValidator {

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private EmployeeMeetingStatusRepo employeeMeetingStatusRepo;

    @Autowired
    private MeetingRoomRepo meetingRoomRepo;


    public void checkEmployeeMeetingConflict(IMeetingServiceDTO meetingDTO, LocalDateTime start, LocalDateTime end, List<EmployeeModel> existingEmployees) throws MeetingException {
        // Check if all employees are free during the meeting time

        List<Integer> employeeIds = meetingDTO.getEmployeeIDs();

        existingEmployees.addAll(employeeRepo.findByEmployeeIdIn(employeeIds));

        List<Integer> existingEmployeeIds = existingEmployees.stream()
                .map(EmployeeModel::getEmployeeId)
                .collect(Collectors.toList());

        List<Integer> missingEmployeeIds = employeeIds.stream()
                .filter(id -> !existingEmployeeIds.contains(id))
                .collect(Collectors.toList());

        if (!missingEmployeeIds.isEmpty()) {
            throw new MeetingException("Employees not found with IDs: " + missingEmployeeIds, 404);
        }

        List<Integer> conflictTimingEmployee = new ArrayList<>();

        for (int employeeId : employeeIds) {
            List<EmployeeMeetingStatusModel> employeeMeetings = employeeMeetingStatusRepo.findMeetingsByEmployeeAndTimeRange(employeeId,start,end);
            if (!employeeMeetings.isEmpty()) {
                conflictTimingEmployee.add(employeeId);
            }
        }

        if(!conflictTimingEmployee.isEmpty()){
            throw new MeetingException("Employee with IDs " + conflictTimingEmployee + " is already booked during the selected time.", 409);
        }
    }

    public List<MeetingRoomModel> checkAvailableRoom(LocalDateTime start, LocalDateTime end, List<EmployeeModel> existingEmployees) {

        // Count employees per office
        Map<Integer, Integer> officeEmployeeCount = new HashMap<>();

        for (EmployeeModel employee : existingEmployees) {
            int officeId = employee.getOffice().getOfficeId();
            officeEmployeeCount.put(officeId, officeEmployeeCount.getOrDefault(officeId, 0) + 1);
        }


        // Sort the offices by the number of employees in descending order
        List<Map.Entry<Integer, Integer>> sortedOffices = new ArrayList<>(officeEmployeeCount.entrySet());
        sortedOffices.sort((a, b) -> b.getValue().compareTo(a.getValue()));

        for (Map.Entry<Integer, Integer> officeEntry : sortedOffices) {
            int officeId = officeEntry.getKey();

            List<MeetingRoomModel> availableRooms = meetingRoomRepo.findAvailableMeetingRooms(officeId,start,end);

            if (!availableRooms.isEmpty()) {
                return availableRooms;
            }
        }
        return Collections.emptyList();
    }

}
