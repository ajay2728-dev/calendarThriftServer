package com.example.calendarThriftServer;

import com.example.calendarThriftServer.model.EmployeeModel;
import com.example.calendarThriftServer.repository.EmployeeRepo;
import com.example.employee.EmployeeInvalidInputException;
import com.example.employee.EmployeeMissingInputException;
import com.example.employee.IEmployee;
import com.example.employee.NonUniqueEmployeeEmailException;
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
import static org.junit.jupiter.api.Assertions.assertThrows;


@ExtendWith(MockitoExtension.class)
public class MeetingHandlerTest {

    private EmployeeModel employee;
    private EmployeeModel savedEmployee;
    private IEmployee inputEmployee;
    private IEmployee missingInputEmployee;
    private IEmployee invalidEmailEmployee;

    @Mock
    EmployeeRepo employeeRepo;

    @InjectMocks
    MeetingHandler meetingHandler;

    @BeforeEach
    void setup(){

        inputEmployee = new IEmployee(1, "John Doe", "john.doe@xyz.com",
                "New York", "Engineering", 50000, true);

        savedEmployee = new EmployeeModel(1, "John Doe", "john.doe@xyz.com",
                "New York", "Engineering", true, 50000);

        missingInputEmployee = new IEmployee(1, "John Doe", null,
                "New York", "Engineering", 50000, true);

        invalidEmailEmployee = new IEmployee(1, "John Doe", "john.doe.com",
                "New York", "Engineering", 50000, true);


    }

    @Test
    public void test_whenAddEmployee_givenValidEmployee_AddEmployeeSuccess() throws TException {

        Mockito.when(employeeRepo.findByEmployeeEmail(Mockito.anyString())).thenReturn(null);
        Mockito.when(employeeRepo.save(Mockito.any(EmployeeModel.class))).thenReturn(savedEmployee);

        IEmployee result= meetingHandler.addEmployee(inputEmployee);

        assertThat(result).isNotNull();
        assertThat(result.getEmployeeId()).isEqualTo(1);
        assertThat(result.getEmployeeName()).isEqualTo("John Doe");
        assertThat(result.getEmployeeEmail()).isEqualTo("john.doe@xyz.com");
    }

    @Test
    public void test_whenAddEmployee_givenMissingInput_AddEmployeeFail(){
         EmployeeMissingInputException thrownException = assertThrows(EmployeeMissingInputException.class,()->{
            meetingHandler.addEmployee(missingInputEmployee);
        }
        );
        assertEquals("Missing Required Input",thrownException.getMessage());
    }

    @Test
    public void test_whenAddEmployee_givenInvalidEmail_AddEmployeeFail() {

        EmployeeInvalidInputException thrownException = assertThrows(EmployeeInvalidInputException.class,()->{
                    meetingHandler.addEmployee(invalidEmailEmployee);
                }
        );
        assertEquals("Invalid Email Format",thrownException.getMessage());

    }

    @Test
    public void test_whenAddEmployee_givenNonUniqueEmployeeEmail_AddEmployeeFail() {

        Mockito.when(employeeRepo.findByEmployeeEmail("john.doe@xyz.com")).thenReturn(Optional.of(new EmployeeModel(1, "John Doe", "john.doe@xyz.com",
                "New York", "Engineering", true, 50000)));

        NonUniqueEmployeeEmailException thrownException = assertThrows(NonUniqueEmployeeEmailException.class,()->{
                    meetingHandler.addEmployee(inputEmployee);
                }
        );
        assertEquals("Provide Different Employee Email",thrownException.getMessage());

    }

}
