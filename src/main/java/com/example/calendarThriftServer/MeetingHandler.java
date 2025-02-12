package com.example.calendarThriftServer;

import com.example.calendarThriftServer.model.EmployeeModel;
import com.example.calendarThriftServer.repository.EmployeeRepo;
import com.example.employee.*;
import org.apache.thrift.TException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

import java.util.Collections;
import java.util.List;
import java.util.MissingFormatArgumentException;
import java.util.Optional;

@Service
public class MeetingHandler implements IEmployeeService.Iface {

    private static final Logger log = LoggerFactory.getLogger(MeetingHandler.class);
    @Autowired
    private EmployeeRepo employeeRepo;

    @Override
    public IEmployee addEmployee(IEmployee emp) throws TException {
        log.info("add employee thrift server");

        // checking missing input field
        if(emp.getEmployeeName()==null || emp.getEmployeeEmail()==null || emp.getDepartment()==null || emp.getOfficeLocation()==null || emp.getSalary()==0){
            throw new EmployeeMissingInputException("Missing Required Input");
        }

        // checking valid employee email format
        if(!emp.getEmployeeEmail().matches("^[A-Za-z0-9._%+-]+@xyz\\.com$")){
            throw new EmployeeInvalidInputException("Invalid Email Format");
        }

        // check unique email
        String emp_email=emp.getEmployeeEmail();
        if(employeeRepo.findByEmployeeEmail(emp_email).isPresent()){
            throw new NonUniqueEmployeeEmailException("Provide Different Employee Email");
        }

        int generateEmployeeId=(int) (employeeRepo.count() + 1);

        EmployeeModel employee = new EmployeeModel(
                generateEmployeeId,
                emp.getEmployeeName(),
                emp.getEmployeeEmail(),
                emp.getOfficeLocation(),
                emp.getDepartment(),
                emp.isIsActive(),
                emp.getSalary() );

      EmployeeModel saveEmployee = employeeRepo.save(employee);

      IEmployee resEmployee = new IEmployee(saveEmployee.getEmployeeId(),
               saveEmployee.getEmployeeName(),
               saveEmployee.getEmployeeEmail(),
               saveEmployee.getOfficeLocation(),
               saveEmployee.getDepartment(),
               saveEmployee.getSalary(),
               saveEmployee.getActive() );

       return resEmployee;
    }

    @Override
    public List<IEmployee> getAllEmployees() throws TException {
        return Collections.emptyList();
    }

    @Override
    public IEmployee getEmployeeById(int employeeId)  {

        // search for employee by id
        Optional<EmployeeModel> employeeOpt = employeeRepo.findById(employeeId);

        // check employee is present
//        if(!employeeOpt.isPresent()){
//            throw
//        }
        EmployeeModel employee = employeeOpt.get();
        IEmployee returnEmployee = new IEmployee(employee.getEmployeeId(),
                employee.getEmployeeName(),
                employee.getEmployeeEmail(),
                employee.getOfficeLocation(),
                employee.getDepartment(),
                employee.getSalary(),
                employee.getActive() );

        // return the employee
        return returnEmployee;
    }

    @Override
    public boolean deleteEmployeeById(int id) throws TException {
        return false;
    }
}
