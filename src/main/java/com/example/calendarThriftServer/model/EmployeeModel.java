package com.example.calendarThriftServer.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class EmployeeModel {

    @Id
    @Column(name = "employeeId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int employeeId;

    @Column(name = "employeeName",nullable = false)
    private String employeeName;

    @Column(name = "employeeEmail", unique = true,nullable = false)
    private String employeeEmail;

    @ManyToOne
    @JoinColumn(name = "officeId", nullable = false)
    private OfficeModel office;

    @Column(name = "department",nullable = false)
    private String department;

    @Column(name = "salary",nullable = false)
    private int salary;

    @Column(name = "isActive",nullable = false)
    Boolean isActive;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<EmployeeMeetingStatusModel> meetingStatuses;

    public EmployeeModel(int employeeId,String employeeName, String employeeEmail, OfficeModel office, String department, Boolean isActive, int salary) {
        this.employeeId=employeeId;
        this.employeeName = employeeName;
        this.employeeEmail = employeeEmail;
        this.office = office;
        this.department = department;
        this.isActive = isActive;
        this.salary = salary;
    }

    public EmployeeModel(String employeeName, String employeeEmail, OfficeModel office, String department, Boolean isActive, int salary) {
        this.employeeName = employeeName;
        this.employeeEmail = employeeEmail;
        this.office = office;
        this.department = department;
        this.isActive = isActive;
        this.salary = salary;
    }

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

    public OfficeModel getOffice() {
        return office;
    }

    public void setOffice(OfficeModel office) {
        this.office = office;
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

}
