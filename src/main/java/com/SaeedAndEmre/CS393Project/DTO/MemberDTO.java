package com.SaeedAndEmre.CS393Project.DTO;

import com.SaeedAndEmre.CS393Project.Entities.Reservation;

import javax.persistence.OneToMany;
import java.util.List;

public class MemberDTO {

    private Long id;
    private String name;
    private String address;
    private String email;
    private String phone;
    private String drivingLicenseNumber;

    public MemberDTO(Long id, String name, String address, String email, String phone, String drivingLicenseNumber) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.drivingLicenseNumber = drivingLicenseNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDrivingLicenseNumber() {
        return drivingLicenseNumber;
    }

    public void setDrivingLicenseNumber(String drivingLicenseNumber) {
        this.drivingLicenseNumber = drivingLicenseNumber;
    }
}