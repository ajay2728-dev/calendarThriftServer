package com.example.calendarManagement.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class MeetingModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( name = "meetingId")
    private int meetingId;

    @Column( name = "description", nullable = false)
    private String description;

    @Column( name = "agenda",nullable = false)
    private String agenda;

    @ManyToOne
    @JoinColumn(name = "roomId", nullable = false)
    private MeetingRoomModel meetingRoom;

    @Column( name = "meetingTime",nullable = false)
    private LocalDateTime meetingTime;

    @Column( name = "isValid")
    private Boolean isValid;

    @OneToMany(mappedBy = "meeting", cascade = CascadeType.ALL)
    private List<MeetingStatusModel> statuses;

    public MeetingModel(){

    }

    public MeetingModel(String description, MeetingRoomModel meetingRoom, String agenda, LocalDateTime meetingTime, Boolean isValid) {
        this.description = description;
        this.meetingRoom = meetingRoom;
        this.agenda = agenda;
        this.meetingTime = meetingTime;
        this.isValid = isValid;
    }

    public int getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(int meetingId) {
        this.meetingId = meetingId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAgenda() {
        return agenda;
    }

    public void setAgenda(String agenda) {
        this.agenda = agenda;
    }

    public MeetingRoomModel getMeetingRoom() {
        return meetingRoom;
    }

    public void setMeetingRoom(MeetingRoomModel meetingRoom) {
        this.meetingRoom = meetingRoom;
    }

    public LocalDateTime getMeetingTime() {
        return meetingTime;
    }

    public void setMeetingTime(LocalDateTime meetingTime) {
        this.meetingTime = meetingTime;
    }

    public Boolean getValid() {
        return isValid;
    }

    public void setValid(Boolean valid) {
        isValid = valid;
    }
}
