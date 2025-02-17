package com.example.calendarManagement.model;

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
}
