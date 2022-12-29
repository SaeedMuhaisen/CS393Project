package com.SaeedAndEmre.CS393Project.Entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Equipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long equipmentId;
    private String name;
    private double price;

    @ManyToMany(mappedBy = "equipments")
    private List<Reservation> reservations = new ArrayList<>();

    public Equipment(Long equipmentId, String name, double price, List<Reservation> reservations) {
        this.equipmentId = equipmentId;
        this.name = name;
        this.price = price;
        this.reservations = reservations;
    }

    public Equipment() {

    }

    public Long getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(Long equipmentId) {
        this.equipmentId = equipmentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }
}