package com.example.calendarThriftServer.repository;

import com.example.calendarThriftServer.model.MeetingStatusModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeetingStatusRepo extends JpaRepository<MeetingStatusModel,Integer> {
}
