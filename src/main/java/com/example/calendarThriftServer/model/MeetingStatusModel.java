package com.example.calendarThriftServer.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class MeetingStatusModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "statusId")
    private int statusId;

    @ManyToOne
    @JoinColumn(name = "meetingId", nullable = false)
    private MeetingModel meeting;

    @Column(name = "status")
    private Boolean status;

    @ManyToMany
    @JoinTable(
            name = "meeting_status_employee",
            joinColumns = @JoinColumn(name = "statusId"),
            inverseJoinColumns = @JoinColumn(name = "employeeId")
    )
    private Set<EmployeeModel> employees;

    public MeetingStatusModel(int statusId, MeetingModel meeting, Boolean status, Set<EmployeeModel> employees) {
        this.statusId = statusId;
        this.meeting = meeting;
        this.status = status;
        this.employees = employees;
    }

    public MeetingStatusModel() {

    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public MeetingModel getMeeting() {
        return meeting;
    }

    public void setMeeting(MeetingModel meeting) {
        this.meeting = meeting;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Set<EmployeeModel> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<EmployeeModel> employees) {
        this.employees = employees;
    }
}
