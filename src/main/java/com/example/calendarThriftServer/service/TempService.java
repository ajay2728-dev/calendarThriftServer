package com.example.calendarThriftServer.service;

import com.example.calendarThriftServer.employeeThrift.Employee;
import com.example.calendarThriftServer.employeeThrift.EmployeeService;
import org.apache.thrift.TException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class TempService implements EmployeeService.Iface{
    @Override
    public int addEmployee(Employee emp) throws TException {
        return 0;
    }

    @Override
    public List<Employee> getAllEmployees() throws TException {
        return Collections.emptyList();
    }

    @Override
    public Employee getEmployeeById(int id) throws TException {
        return null;
    }

    @Override
    public boolean deleteEmployeeById(int id) throws TException {
        return false;
    }
}
