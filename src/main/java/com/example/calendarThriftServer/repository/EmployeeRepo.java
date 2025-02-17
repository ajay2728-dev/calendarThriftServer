package com.example.calendarManagement.repository;

import com.example.calendarManagement.model.EmployeeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepo extends JpaRepository<EmployeeModel,Integer> {
    Optional<EmployeeModel> findByEmployeeEmail(String employeeEmail);
}