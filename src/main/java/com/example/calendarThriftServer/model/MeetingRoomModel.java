package com.example.calendarManagement.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class MeetingRoomModel {

    @Id
    @Column(name = "roomId")
    private int roomId;

    @Column(name = "roomName", unique = true)
    private String roomName;

    @ManyToOne
    @JoinColumn(name = "office_id")
    private OfficeModel office;

    @Column(name = "isEnable")
    private boolean isEnable;

    @OneToMany(mappedBy = "meetingRoom", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MeetingModel> meetings;

    public MeetingRoomModel(){

    }

    public MeetingRoomModel(int roomId, String roomName, OfficeModel office, boolean isEnable) {
        this.roomId = roomId;
        this.roomName = roomName;
        this.office = office;
        this.isEnable = isEnable;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public OfficeModel getOffice() {
        return office;
    }

    public void setOffice(OfficeModel office) {
        this.office = office;
    }

    public boolean getiIsEnable() {
        return isEnable;
    }

    public void setEnable(boolean enable) {
        isEnable = enable;
    }
}
