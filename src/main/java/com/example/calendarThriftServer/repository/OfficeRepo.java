package com.example.calendarThriftServer.repository;

import com.example.calendarThriftServer.model.OfficeModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OfficeRepo extends JpaRepository<OfficeModel,Integer> {
}
