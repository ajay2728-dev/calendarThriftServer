package com.example.calendarThriftServer;

import com.example.calendarThriftServer.model.EmployeeModel;
import com.example.calendarThriftServer.repository.EmployeeRepo;
import com.example.employee.IEmployee;
import com.example.employee.IEmployeeService;
import org.apache.thrift.TException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class MeetingHandler implements IEmployeeService.Iface {

    private static final Logger log = LoggerFactory.getLogger(MeetingHandler.class);
    @Autowired
    private EmployeeRepo employeeRepo;

    @Override
    public IEmployee addEmployee(IEmployee emp) throws TException {
        log.info("add employee thrift server");
        String req_email=emp.getEmployeeEmail();
//        if(req_email==null){
//            return ex
//        }
        int generateEmployeeId=1;
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
    public IEmployee getEmployeeById(int id) throws TException {
        return null;
    }

    @Override
    public boolean deleteEmployeeById(int id) throws TException {
        return false;
    }
}
