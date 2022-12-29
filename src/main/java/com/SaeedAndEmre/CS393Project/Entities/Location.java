package com.SaeedAndEmre.CS393Project.Entities;

import javax.persistence.*;
@Entity
public class Location {
    //TODO: Are we using name and code as id, or just name?
    @Id
    private String name;
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int code;
    private String address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}