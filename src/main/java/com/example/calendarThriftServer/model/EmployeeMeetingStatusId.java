//package com.example.calendarThriftServer.model;
//
//import javax.persistence.Access;
//import javax.persistence.AccessType;
//import javax.persistence.Embeddable;
//import java.io.Serializable;
//import java.util.Objects;
//
//@Embeddable
//@Access(AccessType.FIELD)
//public class EmployeeMeetingStatusId implements Serializable {
//
//    private Integer meetingId;
//    private Integer employeeId;
//
//    public EmployeeMeetingStatusId() {
//    }
//
//    public EmployeeMeetingStatusId(Integer meetingId, Integer employeeId) {
//        this.meetingId = meetingId;
//        this.employeeId = employeeId;
//    }
//
//    public int getMeetingId() {
//        return meetingId;
//    }
//
//    public void setMeetingId(Integer meetingId) {
//        this.meetingId = meetingId;
//    }
//
//    public int getEmployeeId() {
//        return employeeId;
//    }
//
//    public void setEmployeeId(Integer employeeId) {
//        this.employeeId = employeeId;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        EmployeeMeetingStatusId that = (EmployeeMeetingStatusId) o;
//        return meetingId == that.meetingId && employeeId == that.employeeId;
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(meetingId, employeeId);
//    }
//}