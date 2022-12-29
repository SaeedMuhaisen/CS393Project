package com.SaeedAndEmre.CS393Project.Entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Equipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long equipmentId;
    private String name;
    private double price;

    @ManyToMany(mappedBy = "equipments")
    private List<Reservation> reservations = new ArrayList<>();

    public long getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(long equipmentId) {
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