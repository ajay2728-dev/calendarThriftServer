package com.example.calendarThriftServer.repository;

import com.example.calendarThriftServer.model.MeetingRoomModel;
import com.example.calendarThriftServer.model.OfficeModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeetingRoomRepo  extends JpaRepository<MeetingRoomModel,Integer> {
    int countByOffice(OfficeModel office);
}
