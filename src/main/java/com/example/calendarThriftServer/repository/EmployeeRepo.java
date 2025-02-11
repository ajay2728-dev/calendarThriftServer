package com.example.calendarThriftServer.repository;

import com.example.calendarThriftServer.model.EmployeeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepo extends JpaRepository<EmployeeModel,Integer> {
    Optional<EmployeeModel> findByEmployeeEmail(String employeeEmail);
}
