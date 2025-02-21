package com.example.calendarThriftServer;


import com.example.calendarThriftServer.model.*;
import com.example.calendarThriftServer.repository.EmployeeRepo;
import com.example.calendarThriftServer.repository.MeetingRepo;
import com.example.calendarThriftServer.repository.MeetingRoomRepo;
import com.example.calendarThriftServer.repository.MeetingStatusRepo;
import com.example.thriftMeeting.IMeetingServiceDTO;
import com.example.thriftMeeting.IMeetingServiceResponse;
import org.apache.thrift.TException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.time.LocalDateTime;
import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.time.format.DateTimeFormatter;


@ExtendWith(MockitoExtension.class)
public class MeetingHandlerTest {

    @Mock
    private EmployeeRepo employeeRepo;

    @Mock
    private EntityManager entityManager;

    @Mock
    private Query mockQuery;

    @Mock
    private Query query;

    @Mock
    private MeetingRepo meetingRepo;

    @InjectMocks
    private MeetingHandler meetingHandler;

    @Mock
    private MeetingRoomRepo meetingRoomRepo;

    @Mock
    private MeetingStatusRepo meetingStatusRepo;

    private IMeetingServiceDTO meetingDTO;
    private IMeetingServiceDTO inValidInputMeetingDTO;
    private EmployeeModel employee1;
    private EmployeeModel employee2;
    private MeetingRoomModel meetingRoom;
    private OfficeModel office;
    private MeetingModel inputMeeting;
    private MeetingModel meetingModel;
    private MeetingModel inputMeetingModel;
    private List<MeetingModel> mockMeetings;
    private List<MeetingRoomModel> mockRooms;
    private List<MeetingStatusModel> mockMeetingStatus;
    private MeetingStatusModel meetingStatus;


    @BeforeEach
    void setup(){

        office =  new OfficeModel(1,"Headquarters","New York");
        employee1 = new EmployeeModel( 1,"John Doe", "john.doe@xyz.com", office,"Engineering",
                true, 50000 );

        employee2 = new EmployeeModel( 2,"Ajay Singh", "ajay.singh@xyz.com", office,"Engineering",
                true, 50000 );

        meetingRoom = new MeetingRoomModel(1,"Conference xyz",office,true);

        meetingDTO = new IMeetingServiceDTO();
        meetingDTO.setStartTime("2025-02-18 10:00");
        meetingDTO.setEndTime("2025-02-18 11:00");
        meetingDTO.setEmployeeIDs(new ArrayList<>(Arrays.asList(1, 2)));

        inputMeeting = new MeetingModel(
                0,
                "on boarding meeting",
                "check update of intern",
                meetingRoom,
                LocalDateTime.now(),
                LocalDateTime.now().plusHours(1),
                true
        );

        meetingModel = new MeetingModel(
                1,
                "on boarding meeting",
                "check update of intern",
                meetingRoom,
                LocalDateTime.now(),
                LocalDateTime.now().plusHours(1),
                true
        );

        inputMeetingModel = new MeetingModel(
                0,
                "on boarding meeting",
                "check update of intern",
                meetingRoom,
                LocalDateTime.now(),
                LocalDateTime.now().plusHours(1),
                true
        );

        mockMeetings = new ArrayList<>();
        mockMeetings.add(meetingModel);


        inValidInputMeetingDTO = new IMeetingServiceDTO();
        inValidInputMeetingDTO.setStartTime("2025-02-18 10:00");
        inValidInputMeetingDTO.setEndTime("2025-02-18 11:00");
        inValidInputMeetingDTO.setEmployeeIDs(new ArrayList<>(Arrays.asList(1, 2,3)));

        mockRooms = new ArrayList<>();
        mockRooms.add(meetingRoom);

        mockMeetingStatus = new ArrayList<>();

        Set<EmployeeModel> meetingEmployees = new HashSet<>();
        meetingEmployees.add(employee1);
        meetingEmployees.add(employee2);

        meetingStatus = new MeetingStatusModel(1, meetingModel, true, meetingEmployees);
        mockMeetingStatus.add(meetingStatus);
    }

    @Test
    void test_whenCanSchedule_givenValidInput_ScheduleSuccess() throws TException {

         Mockito.when(employeeRepo.findById(Mockito.anyInt())).thenReturn(Optional.of(employee1));
         Mockito.when(meetingStatusRepo.findMeetingsByEmployeeAndTimeRange(Mockito.anyInt(),Mockito.any(),Mockito.any())).thenReturn(Collections.emptyList());
         Mockito.when(meetingRoomRepo.findAvailableMeetingRooms(Mockito.anyInt(),Mockito.any(),Mockito.any())).thenReturn(mockRooms);
         boolean result = meetingHandler.canScheduleMeeting(meetingDTO);
         assertThat(result).isNotNull();
    }

    @Test
    void test_whenCanSchedule_givenInvalidInput_ThrowTException(){

        Mockito.when(employeeRepo.findById(Mockito.anyInt())).thenReturn(Optional.empty());
        TException thrownException = assertThrows(TException.class,()->{
                    meetingHandler.canScheduleMeeting(inValidInputMeetingDTO);
                }
        );
        assertEquals("Employee not found with given employeeId",thrownException.getMessage());
    }

    @Test
    void test_whenCanSchedule_whenEmployeeIsBusy_ThrowsException() {

        Mockito.when(employeeRepo.findById(Mockito.anyInt())).thenReturn(Optional.of(employee1));
        Mockito.when(meetingStatusRepo.findMeetingsByEmployeeAndTimeRange(Mockito.anyInt(),Mockito.any(),Mockito.any())).thenReturn(mockMeetingStatus);

        TException thrownException = assertThrows(TException.class, () -> {
            meetingHandler.canScheduleMeeting(meetingDTO);
        });

        assertEquals("Employee with ID 1 is already booked during the selected time.",thrownException.getMessage());
    }

    @Test
    void test_whenCanSchedule_whenNoMeetingRoomAvailable_ThrowsException() {

        Mockito.when(employeeRepo.findById(Mockito.anyInt())).thenReturn(Optional.of(employee1));
        Mockito.when(meetingRoomRepo.findAvailableMeetingRooms(Mockito.anyInt(),Mockito.any(),Mockito.any())).thenReturn(Collections.emptyList());

        TException thrownException = assertThrows(TException.class, () -> {
            meetingHandler.canScheduleMeeting(meetingDTO);
        });

        assertEquals("No available meeting room for the selected time.",thrownException.getMessage());

    }

    // test case for meeting schedule

    @Test
    void test_whenMeetingSchedule_givenValidInput_scheduleMeetingSuccess() throws TException {

        Mockito.when(employeeRepo.findById(Mockito.anyInt())).thenReturn(Optional.of(employee1));
        Mockito.when(meetingStatusRepo.findMeetingsByEmployeeAndTimeRange(Mockito.anyInt(),Mockito.any(),Mockito.any())).thenReturn(Collections.emptyList());
        Mockito.when(meetingRoomRepo.findById(Mockito.anyInt())).thenReturn(Optional.of(meetingRoom));
        Mockito.lenient().when(meetingRoomRepo.findAvailableMeetingRooms(Mockito.anyInt(),Mockito.any(),Mockito.any())).thenReturn(mockRooms);
        Mockito.when(meetingStatusRepo.save(Mockito.any(MeetingStatusModel.class))).thenReturn(meetingStatus);
        Mockito.when(meetingRepo.save(Mockito.any(MeetingModel.class))).thenReturn(meetingModel);


        meetingDTO.setDescription("on boarding meeting");
        meetingDTO.setAgenda("Check update of intern work");
        IMeetingServiceDTO result = meetingHandler.meetingSchedule(meetingDTO);

        assertThat(result).isNotNull();

    }

    @Test
    void test_whenMeetingSchedule_givenInvalidInput_ThrowTException(){
        Mockito.when(employeeRepo.findById(Mockito.anyInt())).thenReturn(Optional.empty());
        inValidInputMeetingDTO.setDescription("on boarding meeting");
        inValidInputMeetingDTO.setAgenda("Check update of intern work");
        TException thrownException = assertThrows(TException.class,()->{
                    meetingHandler.meetingSchedule(inValidInputMeetingDTO);
                }
        );
        assertEquals("Employee not found with given employeeId",thrownException.getMessage());
    }

    @Test
    void test_whenMeetingSchedule_whenEmployeeIsBusy_ThrowsException() {

        Mockito.when(employeeRepo.findById(Mockito.anyInt())).thenReturn(Optional.of(employee1));
        Mockito.when(meetingStatusRepo.findMeetingsByEmployeeAndTimeRange(Mockito.anyInt(),Mockito.any(),Mockito.any())).thenReturn(mockMeetingStatus);

        meetingDTO.setDescription("on boarding meeting");
        meetingDTO.setAgenda("Check update of intern work");
        TException thrownException = assertThrows(TException.class, () -> {
            meetingHandler.meetingSchedule(meetingDTO);
        });

        assertEquals("Employee with ID 1 is already booked during the selected time.",thrownException.getMessage());
    }

    @Test
    void test_whenMeetingSchedule_whenNoMeetingRoomAvailable_ThrowsException() {

        Mockito.when(employeeRepo.findById(Mockito.anyInt())).thenReturn(Optional.of(employee1));
        Mockito.when(meetingRoomRepo.findAvailableMeetingRooms(Mockito.anyInt(),Mockito.any(),Mockito.any())).thenReturn(Collections.emptyList());

        meetingDTO.setDescription("on boarding meeting");
        meetingDTO.setAgenda("Check update of intern work");
        TException thrownException = assertThrows(TException.class, () -> {
            meetingHandler.meetingSchedule(meetingDTO);
        });

        assertEquals("No available meeting room for the selected time.",thrownException.getMessage());

    }


}
