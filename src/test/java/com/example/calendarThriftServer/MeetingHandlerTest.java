package com.example.calendarThriftServer;

import com.example.calendarThriftServer.model.EmployeeModel;
import com.example.calendarThriftServer.repository.EmployeeRepo;
import com.example.employee.IEmployee;
import org.apache.thrift.TException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(MockitoExtension.class)
public class MeetingHandlerTest {

    private EmployeeModel employee;
    private EmployeeModel savedEmployee;
    private IEmployee inputEmployee;

    @Mock
    EmployeeRepo employeeRepo;

    @InjectMocks
    MeetingHandler meetingHandler;

    @BeforeEach
    void setup(){
        inputEmployee = new IEmployee(0, "John Doe", "john.doe@example.com",
                "New York", "Engineering", 50000, true);

        savedEmployee = new EmployeeModel(1, "John Doe", "john.doe@example.com",
                "New York", "Engineering", true, 50000);
    }

    @Test
    public void test_givenValidEmployee_whenAddEmployee_thenReturnsSavedEmployee() throws TException {

        Mockito.when(employeeRepo.save(Mockito.any(EmployeeModel.class))).thenReturn(savedEmployee);

        IEmployee result= meetingHandler.addEmployee(inputEmployee);

        assertThat(result).isNotNull();
        assertThat(result.getEmployeeId()).isEqualTo(1);
        assertThat(result.getEmployeeName()).isEqualTo("John Doe");
        assertThat(result.getEmployeeEmail()).isEqualTo("john.doe@example.com");
    }
}
