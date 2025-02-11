package com.example.calendarThriftServer.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class EmployeeModel {
    @Id
    @Column(name = "employeeId")
    int employeeId;

    @Column(name = "employeeName")
    String employeeName;

    @Column(name = "employeeEmail")
    String employeeEmail;

    @Column(name =  "officeLocation")
    String officeLocation;

    @Column(name = "department")
    String department;

    @Column(name = "salary")
    int salary;

    @Column(name = "isActive")
    Boolean isActive;

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeEmail() {
        return employeeEmail;
    }

    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }

    public String getOfficeLocation() {
        return officeLocation;
    }

    public void setOfficeLocation(String officeLocation) {
        this.officeLocation = officeLocation;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public EmployeeModel(int employeeId, String employeeName, String employeeEmail, String officeLocation, String department, Boolean isActive, int salary) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.employeeEmail = employeeEmail;
        this.officeLocation = officeLocation;
        this.department = department;
        this.isActive = isActive;
        this.salary = salary;
    }

}
