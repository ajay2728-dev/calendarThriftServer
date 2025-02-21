package com.example.calendarThriftServer.unitTest;

import com.example.calendarThriftServer.model.EmployeeModel;
import com.example.calendarThriftServer.model.MeetingRoomModel;
import com.example.calendarThriftServer.model.MeetingStatusModel;
import com.example.calendarThriftServer.model.OfficeModel;
import com.example.calendarThriftServer.repository.EmployeeRepo;
import com.example.calendarThriftServer.repository.MeetingRoomRepo;
import com.example.calendarThriftServer.repository.MeetingStatusRepo;
import com.example.calendarThriftServer.validator.MeetingHandlerValidator;
import com.example.thriftMeeting.IMeetingServiceDTO;
import com.example.thriftMeeting.MeetingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class MeetingHandlerValidatorTest {
    @Mock
    private EmployeeRepo employeeRepo;

    @Mock
    private MeetingStatusRepo meetingStatusRepo;

    @Mock
    private MeetingRoomRepo meetingRoomRepo;

    @InjectMocks
    private MeetingHandlerValidator meetingHandlerValidator;

    private IMeetingServiceDTO meetingDTO;
    private MeetingRoomModel meetingRoom;
    private EmployeeModel employee1;
    private OfficeModel office;
    private LocalDateTime start;
    private LocalDateTime end;

    @BeforeEach
    void setup(){
        office =  new OfficeModel(1,"Headquarters","New York");
        employee1 = new EmployeeModel( 1,"John Doe", "john.doe@xyz.com", office,"Engineering",
                true, 50000 );
        meetingDTO = new IMeetingServiceDTO();
        meetingDTO.setStartTime("2025-02-18 10:00");
        meetingDTO.setEndTime("2025-02-18 11:00");
        meetingDTO.setEmployeeIDs(new ArrayList<>(Arrays.asList(1, 2)));

        meetingRoom = new MeetingRoomModel(1,"Conference xyz",office,true);

        start = LocalDateTime.of(2025, 2, 21, 10, 0);
        end = LocalDateTime.of(2025, 2, 21, 11, 0);
    }

    @Test
    void test_CheckEmployeeMeetingConflict_NoConflict(){
        Mockito.when(employeeRepo.findById(Mockito.anyInt())).thenReturn(Optional.of(employee1));
        Mockito.when(meetingStatusRepo.findMeetingsByEmployeeAndTimeRange(Mockito.anyInt(), Mockito.any(), Mockito.any())).thenReturn(Collections.emptyList());
        assertDoesNotThrow(() -> meetingHandlerValidator.checkEmployeeMeetingConflict(meetingDTO, start, end));
    }

    @Test
    void test_CheckEmployeeMeetingConflict_EmployeeNotFound(){
        Mockito.when(employeeRepo.findById(Mockito.anyInt())).thenReturn(Optional.empty());
        MeetingException exception = assertThrows(MeetingException.class, () ->
                meetingHandlerValidator.checkEmployeeMeetingConflict(meetingDTO, start, end));

        assertEquals("Employee not found with given employeeId: 1", exception.getMessage());
    }

    @Test
    void test_CheckEmployeeMeetingConflict_EmployeeHasConflict(){
        Mockito.when(employeeRepo.findById(Mockito.anyInt())).thenReturn(Optional.of(employee1));
        List<MeetingStatusModel> conflictingMeetings = Arrays.asList(new MeetingStatusModel());
        Mockito.when(meetingStatusRepo.findMeetingsByEmployeeAndTimeRange(Mockito.anyInt(), Mockito.any(), Mockito.any())).thenReturn(conflictingMeetings);
        MeetingException exception = assertThrows(MeetingException.class, () ->
                meetingHandlerValidator.checkEmployeeMeetingConflict(meetingDTO, start, end));

        assertEquals("Employee with ID 1 is already booked during the selected time.", exception.getMessage());
    }

    @Test
    void test_CheckAvailableRoom_Success(){
        Mockito.when(employeeRepo.findById(Mockito.anyInt())).thenReturn(Optional.of(employee1));
        Mockito.when(meetingRoomRepo.findAvailableMeetingRooms(Mockito.anyInt(), Mockito.any(), Mockito.any())).thenReturn(Arrays.asList(meetingRoom));
        MeetingRoomModel availableRoom = meetingHandlerValidator.checkAvailableRoom(meetingDTO, start, end);

        assertNotNull(availableRoom);
    }

}
