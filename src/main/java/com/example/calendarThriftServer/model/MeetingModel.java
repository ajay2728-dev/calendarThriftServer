package com.example.calendarThriftServer.model;

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

    @Column( name = "startTime",nullable = false)
    private LocalDateTime startTime;

    @Column( name = "endTime",nullable = false)
    private LocalDateTime endTime;

    @Column( name = "isValid")
    private Boolean isValid;

    @OneToMany(mappedBy = "meeting", cascade = CascadeType.ALL)
    private List<MeetingStatusModel> statuses;

    public MeetingModel(){

    }

    public MeetingModel( String description, String agenda, MeetingRoomModel meetingRoom, LocalDateTime startTime, LocalDateTime endTime, Boolean isValid) {
        this.description = description;
        this.agenda = agenda;
        this.meetingRoom = meetingRoom;
        this.startTime = startTime;
        this.endTime = endTime;
        this.isValid = isValid;
    }

    public MeetingModel(int meetingId, String description, String agenda, MeetingRoomModel meetingRoom, LocalDateTime startTime, LocalDateTime endTime, Boolean isValid) {
        this.meetingId = meetingId;
        this.description = description;
        this.agenda = agenda;
        this.meetingRoom = meetingRoom;
        this.startTime = startTime;
        this.endTime = endTime;
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

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public Boolean getValid() {
        return isValid;
    }

    public void setValid(Boolean valid) {
        isValid = valid;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public List<MeetingStatusModel> getStatuses() {
        return statuses;
    }

    public void setStatuses(List<MeetingStatusModel> statuses) {
        this.statuses = statuses;
    }
}
