package com.example.calendarThriftServer.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class EmployeeMeetingStatusModel {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "meetingId", nullable = false)
    private MeetingModel meeting;

    @Column(name = "meetingStatus", nullable = false)
    private Boolean meetingStatus;

    @ManyToOne
    @JoinColumn(name = "employeeId", nullable = false)
    private EmployeeModel employee;


    public EmployeeMeetingStatusModel(MeetingModel meeting, Boolean meetingStatus, EmployeeModel employee) {
        this.meeting = meeting;
        this.meetingStatus = meetingStatus;
        this.employee = employee;
    }

    public EmployeeMeetingStatusModel(){

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
