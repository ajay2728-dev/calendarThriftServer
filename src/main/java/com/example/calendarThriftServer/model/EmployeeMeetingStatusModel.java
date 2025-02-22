package com.example.calendarThriftServer.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class EmployeeMeetingStatusModel {

    @EmbeddedId
    private EmployeeMeetingStatusId id;

    @ManyToOne
    @MapsId("meetingId")
    @JoinColumn(name = "meetingId", nullable = false)
    private MeetingModel meeting;

    @Column(name = "meetingStatus", nullable = false)
    private Boolean meetingStatus;

    @ManyToOne
    @MapsId("employeeId")
    @JoinColumn(name = "employeeId", nullable = false)
    private EmployeeModel employee;


    public EmployeeMeetingStatusModel(MeetingModel meeting, Boolean meetingStatus, EmployeeModel employee) {
        this.id = new EmployeeMeetingStatusId(meeting.getMeetingId(), employee.getEmployeeId());
        this.meeting = meeting;
        this.meetingStatus = meetingStatus;
        this.employee = employee;
    }

    public EmployeeMeetingStatusModel(){

    }

    public EmployeeMeetingStatusId getId() {
        return id;
    }

    public void setId(EmployeeMeetingStatusId id) {
        this.id = id;
    }

    public MeetingModel getMeeting() {
        return meeting;
    }

    public void setMeeting(MeetingModel meeting) {
        this.meeting = meeting;
    }

    public Boolean getMeetingStatus() {
        return meetingStatus;
    }

    public void setMeetingStatus(Boolean meetingStatus) {
        this.meetingStatus = meetingStatus;
    }

    public EmployeeModel getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeModel employee) {
        this.employee = employee;
    }
}
