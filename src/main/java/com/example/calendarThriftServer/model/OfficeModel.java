package com.example.calendarManagement.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class OfficeModel {

    @Id
    @Column(name = "officeId")
    int officeId;

    @Column(name = "officeName", unique = true)
    String officeName;

    @Column(name = "officeLocation")
    String officeLocation;

    @OneToMany(mappedBy = "office", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MeetingRoomModel> meetingRooms = new ArrayList<>();

    @OneToMany(mappedBy = "office", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EmployeeModel> employees = new ArrayList<>();

    public OfficeModel(){

    }

    public OfficeModel(int officeId, String officeName, String officeLocation) {
        this.officeId = officeId;
        this.officeName = officeName;
        this.officeLocation = officeLocation;
    }

    public int getOfficeId() {
        return officeId;
    }

    public void setOfficeId(int officeId) {
        this.officeId = officeId;
    }

    public String getOfficeName() {
        return officeName;
    }

    public void setOfficeName(String officeName) {
        this.officeName = officeName;
    }

    public String getOfficeLocation() {
        return officeLocation;
    }

    public void setOfficeLocation(String officeLocation) {
        this.officeLocation = officeLocation;
    }

}
