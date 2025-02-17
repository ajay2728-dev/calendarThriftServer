package com.example.calendarManagement.repository;

import com.example.calendarManagement.model.OfficeModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OfficeRepo extends JpaRepository<OfficeModel,Integer> {
}
