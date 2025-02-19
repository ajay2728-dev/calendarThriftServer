package com.example.calendarThriftServer;


import com.example.calendarThriftServer.model.*;
import com.example.calendarThriftServer.repository.EmployeeRepo;
import com.example.calendarThriftServer.repository.MeetingRepo;
import com.example.calendarThriftServer.repository.MeetingRoomRepo;
import com.example.thriftMeeting.IMeetingServiceDTO;
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
import static org.mockito.ArgumentMatchers.anyString;


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

    private IMeetingServiceDTO meetingDTO;
    private IMeetingServiceDTO inValidInputMeetingDTO;
    private EmployeeModel employee1;
    private EmployeeModel employee2;
    private MeetingRoomModel meetingRoom;
    private OfficeModel office;
    private MeetingModel meetingModel;
    private MeetingModel inputMeetingModel;
    private List<MeetingModel> mockMeetings;


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

        mockMeetings = Arrays.asList(meetingModel);

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


        inValidInputMeetingDTO = new IMeetingServiceDTO();
        meetingDTO.setStartTime("2025-02-18 10:00");
        meetingDTO.setEndTime("2025-02-18 11:00");
        meetingDTO.setEmployeeIDs(new ArrayList<>(Arrays.asList(1, 2,3)));

        Mockito.when(entityManager.createQuery(Mockito.anyString())).thenReturn(mockQuery);
        Mockito.when(mockQuery.getResultList()).thenReturn(Collections.emptyList());
    }

    @Test
    void test_whenCanSchedule_givenValidInput_ScheduleSuccess() throws TException {

        for(int i : meetingDTO.getEmployeeIDs()){
            Mockito.when(employeeRepo.findById(i)).thenReturn(Optional.of(new EmployeeModel(i, "Employee " + i, "email" + i + "@xyz.com", office, "Engineering", true, 50000)));
        }

        Mockito.when(mockQuery.getResultList()).thenReturn(Collections.singletonList(meetingRoom));

        boolean result = meetingHandler.canScheduleMeeting(meetingDTO);

        assertThat(result).isEqualTo(true);

    }

    @Test
    void test_whenCanSchedule_givenInvalidInput_ThrowTException(){

        Mockito.when(employeeRepo.findById(3)).thenReturn(Optional.empty());
        TException thrownException = assertThrows(TException.class,()->{
                    meetingHandler.canScheduleMeeting(inValidInputMeetingDTO);
                }
        );
        assertEquals("Employee with ID 3 not found",thrownException.getMessage());
    }

    @Test
    void test_whenCanSchedule_whenEmployeeIsBusy_ThrowsException() {

        Mockito.when(mockQuery.getResultList()).thenReturn(Collections.singletonList(new Object()));

        TException thrownException = assertThrows(TException.class, () -> {
            meetingHandler.canScheduleMeeting(meetingDTO);
        });

        assertEquals("Employee with ID 3 is already booked during the selected time.",thrownException.getMessage());
    }

    @Test
    void test_whenCanSchedule_whenNoMeetingRoomAvailable_ThrowsException() {

        Mockito.when(mockQuery.getResultList()).thenReturn(Collections.emptyList());

        TException thrownException = assertThrows(TException.class, () -> {
            meetingHandler.canScheduleMeeting(meetingDTO);
        });

        assertEquals("No available meeting room for the selected time.",thrownException.getMessage());

    }

    // test case for meeting schedule

    @Test
    void test_whenMeetingSchedule_givenValidInput_scheduleMeetingSuccess() throws TException {

        meetingDTO.setDescription("on boarding meeting");
        meetingDTO.setAgenda("check update of intern");
        meetingDTO.setRoomId(2);

        Mockito.when(employeeRepo.findById(Mockito.anyInt())).thenReturn(Optional.of(employee1));

        Mockito.when(entityManager.createQuery(Mockito.anyString())).thenReturn(query);
        Mockito.when(query.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(query);
        Mockito.when(query.getResultList()).thenReturn(Collections.emptyList());

        Mockito.when(meetingRoomRepo.findById(Mockito.anyInt())).thenReturn(Optional.of(meetingRoom));

        Mockito.when(entityManager.createQuery(Mockito.anyString())).thenReturn(query);
        Mockito.when(query.setParameter(Mockito.anyString(), Mockito.any())).thenReturn(query);
        Mockito.when(query.getResultList()).thenReturn(Collections.singletonList(new MeetingRoomModel()));

        Mockito.when(meetingRepo.save(inputMeetingModel)).thenReturn(meetingModel);

        IMeetingServiceDTO response = meetingHandler.meetingSchedule(meetingDTO);

        assertThat(response).isNotNull();

    }


}
