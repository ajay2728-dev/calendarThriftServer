package com.example.calendarThriftServer;

import com.example.calendarThriftServer.model.EmployeeModel;
import com.example.calendarThriftServer.model.MeetingModel;
import com.example.calendarThriftServer.model.MeetingRoomModel;
import com.example.calendarThriftServer.model.EmployeeMeetingStatusModel;
import com.example.calendarThriftServer.repository.EmployeeRepo;
import com.example.calendarThriftServer.repository.MeetingRepo;
import com.example.calendarThriftServer.repository.MeetingRoomRepo;
import com.example.calendarThriftServer.repository.EmployeeMeetingStatusRepo;
import com.example.calendarThriftServer.validator.MeetingHandlerValidator;
import com.example.thriftMeeting.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;


@Slf4j
@Service
public class MeetingHandler implements IMeetingService.Iface {

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private MeetingRepo meetingRepo;

    @Autowired
    private MeetingRoomRepo meetingRoomRepo;

    @Autowired
    private EmployeeMeetingStatusRepo employeeMeetingStatusRepo;

    @Autowired
    private MeetingHandlerValidator meetingHandlerValidator;


    @Override
    @Transactional
    public boolean canScheduleMeeting(IMeetingServiceDTO meetingDTO) throws MeetingException {

        // Parse startTime and endTime as LocalDateTime
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime start = LocalDateTime.parse(meetingDTO.getStartTime(), formatter);
        LocalDateTime end = LocalDateTime.parse(meetingDTO.getEndTime(), formatter);

        List<EmployeeModel> existingEmployees = new ArrayList<>();

        // checking employee timing conflict
        log.info("checking employee timing conflict ...");
        meetingHandlerValidator.checkEmployeeMeetingConflict(meetingDTO,start,end, existingEmployees);
        log.info("checked employee timing conflict ...");

        // checking available room
        log.info("checking for available room ...");
        List<MeetingRoomModel> roomsAvailable = meetingHandlerValidator.checkAvailableRoom(start, end, existingEmployees);
        log.info("checked for available room ...");

        if(roomsAvailable.isEmpty()){
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

        List<EmployeeModel> existingEmployees = new ArrayList<>();

        // checking employee timing conflict
        log.info("checking employee timing conflict ...");
        meetingHandlerValidator.checkEmployeeMeetingConflict(meetingDTO,start,end,existingEmployees);
        log.info("checked employee timing conflict ...");


        // checking available room
        log.info("checking for available room ...");
        List<MeetingRoomModel> roomsAvailable = meetingHandlerValidator.checkAvailableRoom(start, end, existingEmployees);
        log.info("checked for available room ...");

        // check for valid room
        Optional<MeetingRoomModel> givenRoomOpt = meetingRoomRepo.findById(meetingDTO.getRoomId());

        if( roomsAvailable.isEmpty() ){
            throw new MeetingException("No available meeting room for the selected time.",400);
        }

        List<Integer> roomsAvailableId = roomsAvailable.stream().map(room-> room.getRoomId() ).
                collect(Collectors.toList());

        if( !roomsAvailable.contains(givenRoomOpt.get())){
            throw new MeetingException("Given room is not available but this are the available rooms "+roomsAvailableId,400);
        }

        Optional<MeetingRoomModel> validRoomOpt;

        validRoomOpt = (givenRoomOpt.isPresent() && roomsAvailable.contains(givenRoomOpt.get()))
                ? givenRoomOpt
                : (roomsAvailable.isEmpty()
                ? Optional.empty()
                : Optional.of(roomsAvailable.get(0)));

        MeetingRoomModel validRoom = validRoomOpt.get();
        MeetingModel newMeeting = new MeetingModel(meetingDTO.getDescription(),meetingDTO.getAgenda(),validRoom,start,end,true);
        MeetingModel saveMeeting = meetingRepo.save(newMeeting);

        List<EmployeeMeetingStatusModel> meetingStatuses = existingEmployees.stream()
                .map(employee -> {
                    EmployeeMeetingStatusModel meetingStatus = new EmployeeMeetingStatusModel();
                    meetingStatus.setMeeting(saveMeeting);
                    meetingStatus.setMeetingStatus(true);
                    meetingStatus.setEmployee(employee);
                    return meetingStatus;
                })
                .collect(Collectors.toList());

        employeeMeetingStatusRepo.saveAll(meetingStatuses);

        IMeetingServiceDTO responseBody = new IMeetingServiceDTO(saveMeeting.getMeetingId(),saveMeeting.getDescription(),saveMeeting.getAgenda(),meetingDTO.getEmployeeIDs(),meetingDTO.getStartTime(),meetingDTO.getEndTime(),saveMeeting.getMeetingRoom().getRoomId());
        return responseBody;

    }

}
