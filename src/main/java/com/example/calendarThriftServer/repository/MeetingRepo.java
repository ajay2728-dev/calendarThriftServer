package com.example.calendarThriftServer.repository;

import com.example.calendarThriftServer.model.MeetingModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeetingRepo extends JpaRepository<MeetingModel,Integer> {
}
