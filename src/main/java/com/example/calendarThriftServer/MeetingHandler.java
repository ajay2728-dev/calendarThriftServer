package com.example.calendarThriftServer;

import com.example.calendarThriftServer.model.EmployeeModel;
import com.example.calendarThriftServer.model.MeetingModel;
import com.example.calendarThriftServer.model.MeetingRoomModel;
import com.example.calendarThriftServer.model.MeetingStatusModel;
import com.example.calendarThriftServer.repository.EmployeeRepo;
import com.example.calendarThriftServer.repository.MeetingRepo;
import com.example.calendarThriftServer.repository.MeetingRoomRepo;
import com.example.calendarThriftServer.repository.MeetingStatusRepo;
import com.example.calendarThriftServer.validator.MeetingHandlerValidator;
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

    @Autowired
    private MeetingHandlerValidator meetingHandlerValidator;


    @Override
    @Transactional
    public boolean canScheduleMeeting(IMeetingServiceDTO meetingDTO) throws MeetingException {

        // Parse startTime and endTime as LocalDateTime
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime start = LocalDateTime.parse(meetingDTO.getStartTime(), formatter);
        LocalDateTime end = LocalDateTime.parse(meetingDTO.getEndTime(), formatter);

        // checking employee timing conflict
        meetingHandlerValidator.checkEmployeeMeetingConflict(meetingDTO,start,end);

        // checking available room
        MeetingRoomModel roomAvailable = meetingHandlerValidator.checkAvailableRoom(meetingDTO,start,end);

        if(roomAvailable==null){
            throw new MeetingException("No available meeting room for the selected time.",409);
        }

        return true;

    }


    @Override
    @Transactional
    public IMeetingServiceDTO meetingSchedule(IMeetingServiceDTO meetingDTO) throws TException {

//          Parse startTime and endTime as LocalDateTime
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime start = LocalDateTime.parse(meetingDTO.getStartTime(), formatter);
        LocalDateTime end = LocalDateTime.parse(meetingDTO.getEndTime(), formatter);

        // checking employee timing conflict
        meetingHandlerValidator.checkEmployeeMeetingConflict(meetingDTO,start,end);

        // check for valid room
        Optional<MeetingRoomModel> givenRoomOpt = meetingRoomRepo.findById(meetingDTO.getRoomId());

        if(givenRoomOpt.isPresent()){
            // check room is available between start and end time
            MeetingRoomModel validRoom = givenRoomOpt.get();
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
            IMeetingServiceDTO responseBody= new IMeetingServiceDTO(saveMeeting.getMeetingId(),saveMeeting.getDescription(),saveMeeting.getAgenda(),meetingDTO.getEmployeeIDs(),meetingDTO.getStartTime(),meetingDTO.getEndTime(),saveMeeting.getMeetingRoom().getRoomId());
            return responseBody;
        }


        // checking available room
        MeetingRoomModel roomAvailable = meetingHandlerValidator.checkAvailableRoom(meetingDTO,start,end);
        if(roomAvailable==null){
            throw new MeetingException("No available meeting room for the selected time.",409);
        }

        MeetingModel newMeeting = new MeetingModel(meetingDTO.getDescription(),meetingDTO.getAgenda(),roomAvailable,start,end,true);
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
        IMeetingServiceDTO responseBody= new IMeetingServiceDTO(saveMeeting.getMeetingId(),saveMeeting.getDescription(),saveMeeting.getAgenda(),meetingDTO.getEmployeeIDs(),meetingDTO.getStartTime(),meetingDTO.getEndTime(),saveMeeting.getMeetingRoom().getRoomId());
        return responseBody;

    }
}
