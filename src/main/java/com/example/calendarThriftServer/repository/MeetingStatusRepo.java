package com.example.calendarThriftServer.repository;

import com.example.calendarThriftServer.model.MeetingStatusModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public interface MeetingStatusRepo extends JpaRepository<MeetingStatusModel,Integer> {
    @Query(value = "SELECT * FROM meeting_status_model ms " +
            "JOIN meeting_status_employee mse ON ms.status_id = mse.status_id " +
            "JOIN meeting_model m ON ms.meeting_id = m.meeting_id " +
            "WHERE (m.start_time BETWEEN :start AND :end OR m.end_time BETWEEN :start AND :end) " +
            "AND mse.employee_id = :employeeId AND ms.status = true",
            nativeQuery = true)
    List<MeetingStatusModel> findMeetingsByEmployeeAndTimeRange(
            @Param("employeeId") int employeeId,
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end);

}
