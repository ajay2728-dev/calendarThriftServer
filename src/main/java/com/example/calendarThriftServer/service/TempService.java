package com.example.calendarThriftServer.service;

import com.example.calendarThriftServer.employeeThrift.IEmployee;
import com.example.calendarThriftServer.employeeThrift.IEmployeeService;
import org.apache.thrift.TException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class TempService implements IEmployeeService.Iface{
    private static final Logger log = LoggerFactory.getLogger(TempService.class);

    @Override
    public int addEmployee(IEmployee emp) throws TException {
        log.info("add employee service....");
        return 0;
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
