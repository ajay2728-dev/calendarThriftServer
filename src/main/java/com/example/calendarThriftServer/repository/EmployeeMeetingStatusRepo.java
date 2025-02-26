package com.example.calendarThriftServer.repository;

import com.example.calendarThriftServer.model.EmployeeMeetingStatusModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EmployeeMeetingStatusRepo extends JpaRepository<EmployeeMeetingStatusModel,Integer> {
    @Query("SELECT ms FROM EmployeeMeetingStatusModel ms " +
            "WHERE ms.employee.employeeId = :employeeId " +
            "AND (:start BETWEEN ms.meeting.startTime AND ms.meeting.endTime " +
            " OR :end BETWEEN ms.meeting.startTime AND ms.meeting.endTime " +
            " OR ms.meeting.startTime BETWEEN :start AND :end) " +
//            " OR ms.meeting.endTime BETWEEN :start AND :end) "+
            "AND ms.meetingStatus = true")
    List<EmployeeMeetingStatusModel> findMeetingsByEmployeeAndTimeRange(
            @Param("employeeId") int employeeId,
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end);

}
