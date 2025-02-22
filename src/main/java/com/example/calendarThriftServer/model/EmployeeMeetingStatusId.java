package com.example.calendarThriftServer.model;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class EmployeeMeetingStatusId implements Serializable {

    private int meetingId;
    private int employeeId;

    public EmployeeMeetingStatusId() {
    }

    public EmployeeMeetingStatusId(int meetingId, int employeeId) {
        this.meetingId = meetingId;
        this.employeeId = employeeId;
    }

    public int getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(int meetingId) {
        this.meetingId = meetingId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeMeetingStatusId that = (EmployeeMeetingStatusId) o;
        return meetingId == that.meetingId && employeeId == that.employeeId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(meetingId, employeeId);
    }
}