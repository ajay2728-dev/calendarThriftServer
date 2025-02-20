package com.example.calendarThriftServer;

import com.example.calendarThriftServer.model.EmployeeModel;
import com.example.calendarThriftServer.model.MeetingModel;
import com.example.calendarThriftServer.model.MeetingRoomModel;
import com.example.calendarThriftServer.model.MeetingStatusModel;
import com.example.calendarThriftServer.repository.EmployeeRepo;
import com.example.calendarThriftServer.repository.MeetingRepo;
import com.example.calendarThriftServer.repository.MeetingRoomRepo;
import com.example.calendarThriftServer.repository.MeetingStatusRepo;
import com.example.thriftMeeting.*;
import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


@Service
public class MeetingHandler implements IMeetingService.Iface {

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private MeetingRepo meetingRepo;

    @Autowired
    private MeetingRoomRepo meetingRoomRepo;

    @Autowired
    private MeetingStatusRepo meetingStatusRepo;


    @Override
    @Transactional
    public IMeetingServiceResponse canScheduleMeeting(IMeetingServiceDTO meetingDTO) throws TException {

        // Parse startTime and endTime as LocalDateTime
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime start = LocalDateTime.parse(meetingDTO.getStartTime(), formatter);
        LocalDateTime end = LocalDateTime.parse(meetingDTO.getEndTime(), formatter);

        // Check if all employees are free during the meeting time
        for (int employeeId : meetingDTO.getEmployeeIDs()) {
            Optional<EmployeeModel> employeeOpt = employeeRepo.findById(employeeId);
            if (!employeeOpt.isPresent()) {
                IMeetingServiceResponse response = new IMeetingServiceResponse(404,"Employee not found with given employeeId: " + employeeId,null);
                return response;
            }

            List<MeetingStatusModel> employeeMeetings = meetingStatusRepo.findMeetingsByEmployeeAndTimeRange(employeeId,start,end);
            if (!employeeMeetings.isEmpty()) {
                IMeetingServiceResponse response = new IMeetingServiceResponse(409,"Employee with ID " + employeeId + " is already booked during the selected time.",null);
                return  response;
            }
        }

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
                IMeetingServiceResponse response = new IMeetingServiceResponse(200,"Meeting can be Schedule",null);
                return response;
            }
        }

        IMeetingServiceResponse response = new IMeetingServiceResponse(409,"No available meeting room for the selected time.",null);
        return response;

    }


    @Override
    @Transactional
    public IMeetingServiceResponse meetingSchedule(IMeetingServiceDTO meetingDTO) throws TException {

        // Parse startTime and endTime as LocalDateTime
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime start = LocalDateTime.parse(meetingDTO.getStartTime(), formatter);
        LocalDateTime end = LocalDateTime.parse(meetingDTO.getEndTime(), formatter);

        // Check if all employees are free during the meeting time
        for (int employeeId : meetingDTO.getEmployeeIDs()) {
            Optional<EmployeeModel> employeeOpt = employeeRepo.findById(employeeId);
            if (!employeeOpt.isPresent()) {
                IMeetingServiceResponse response = new IMeetingServiceResponse(404,"Employee not found with given employeeId: " + employeeId,null);
                return response;
            }

            List<MeetingStatusModel> employeeMeetings = meetingStatusRepo.findMeetingsByEmployeeAndTimeRange(employeeId,start,end);
            if (!employeeMeetings.isEmpty()) {
                IMeetingServiceResponse response = new IMeetingServiceResponse(409,"Employee with ID " + employeeId + " is already booked during the selected time.",null);
                return  response;
            }
        }

        // check for valid room
        Optional<MeetingRoomModel> validRoomOpt = meetingRoomRepo.findById(meetingDTO.getRoomId());
        if(validRoomOpt.isPresent()){

            // check room is available between start and end time

            MeetingRoomModel validRoom = validRoomOpt.get();
            MeetingModel newMeeting = new MeetingModel(meetingDTO.getDescription(),meetingDTO.getAgenda(),validRoom,start,end,true);
            MeetingModel saveMeeting = meetingRepo.save(newMeeting);

            Set<EmployeeModel> employeeSet = new HashSet<>();
            for (int employeeId : meetingDTO.getEmployeeIDs()) {
                Optional<EmployeeModel> employeeOpt = employeeRepo.findById(employeeId);
                employeeOpt.ifPresent(employeeSet::add);
            }

            MeetingStatusModel meetingStatus = new MeetingStatusModel();
            meetingStatus.setMeeting(saveMeeting);
            meetingStatus.setStatus(false);
            meetingStatus.setEmployees(employeeSet);
            meetingStatusRepo.save(meetingStatus);
            IMeetingServiceDTO body= new IMeetingServiceDTO(saveMeeting.getMeetingId(),saveMeeting.getDescription(),saveMeeting.getAgenda(),meetingDTO.getEmployeeIDs(),meetingDTO.getStartTime(),meetingDTO.getEndTime(),saveMeeting.getMeetingRoom().getRoomId());
            IMeetingServiceResponse response = new IMeetingServiceResponse(200,"Meeting can be Schedule",body);
            return response;
        }

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

            if(!availableRooms.isEmpty()){
                MeetingModel newMeeting = new MeetingModel(meetingDTO.getDescription(),meetingDTO.getAgenda(),availableRooms.get(0),start,end,true);
                MeetingModel saveMeeting = meetingRepo.save(newMeeting);

                Set<EmployeeModel> employeeSet = new HashSet<>();
                for (int employeeId : meetingDTO.getEmployeeIDs()) {
                    Optional<EmployeeModel> employeeOpt = employeeRepo.findById(employeeId);
                    employeeOpt.ifPresent(employeeSet::add);
                }

                MeetingStatusModel meetingStatus = new MeetingStatusModel();
                meetingStatus.setMeeting(saveMeeting);
                meetingStatus.setStatus(false);
                meetingStatus.setEmployees(employeeSet);
                meetingStatusRepo.save(meetingStatus);
                IMeetingServiceDTO body= new IMeetingServiceDTO(saveMeeting.getMeetingId(),saveMeeting.getDescription(),saveMeeting.getAgenda(),meetingDTO.getEmployeeIDs(),meetingDTO.getStartTime(),meetingDTO.getEndTime(),saveMeeting.getMeetingRoom().getRoomId());
                IMeetingServiceResponse response = new IMeetingServiceResponse(200,"Meeting can be Schedule",body);
                return response;
            }
        }

        IMeetingServiceResponse response = new IMeetingServiceResponse(409,"No available meeting room for the selected time.",null);
        return response;
    }
}
