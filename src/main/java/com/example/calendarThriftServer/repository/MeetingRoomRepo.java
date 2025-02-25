package com.example.calendarThriftServer.repository;

import com.example.calendarThriftServer.model.MeetingRoomModel;
import com.example.calendarThriftServer.model.OfficeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MeetingRoomRepo  extends JpaRepository<MeetingRoomModel,Integer> {
    @Query(value = "SELECT mr.* FROM meeting_room_model mr " +
            "WHERE mr.office_id = :officeId " +
            "AND mr.is_enable = true " +
            "AND NOT EXISTS ( " +
            "    SELECT 1 FROM meeting_model m " +
            "    WHERE m.room_id = mr.room_id" +
            "    AND (:start BETWEEN m.start_time AND m.end_time " +
            "    OR :end BETWEEN m.start_time AND m.end_time " +
            "    OR m.start_time BETWEEN :start AND :end " +
            "    OR m.end_time BETWEEN :start AND :end))",
            nativeQuery = true)
    List<MeetingRoomModel> findAvailableMeetingRooms(
            @Param("officeId") int officeId,
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end);

}
