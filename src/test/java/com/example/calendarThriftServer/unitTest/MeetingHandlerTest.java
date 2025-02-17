package com.example.calendarThriftServer.unitTest;


import com.example.calendarThriftServer.MeetingHandler;
import com.example.calendarThriftServer.repository.EmployeeRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityManager;
import javax.persistence.Query;

@ExtendWith(MockitoExtension.class)
public class MeetingHandlerTest {

    @InjectMocks
    private MeetingHandler meetingHandler;

    @Mock
    private EmployeeRepo employeeRepo;

    @Mock
    private EntityManager entityManager;

    @Mock
    private Query query;

    @Test
    void test_canScheduleMeeting_validInput_Success(){

    }


}
