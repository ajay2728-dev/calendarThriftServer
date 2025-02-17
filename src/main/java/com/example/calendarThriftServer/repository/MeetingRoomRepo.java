package com.example.calendarManagement.repository;

import com.example.calendarManagement.model.MeetingRoomModel;
import com.example.calendarManagement.model.OfficeModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeetingRoomRepo  extends JpaRepository<MeetingRoomModel,Integer> {
    int countByOffice(OfficeModel office);
}
