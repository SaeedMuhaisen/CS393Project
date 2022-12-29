package com.SaeedAndEmre.CS393Project.Entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Services {
    //TODO: do we must make id long?
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long serviceId;

    private String name;
    private double price;

    @ManyToMany(mappedBy = "services")
    private List<Reservation> reservations = new ArrayList<>();

    public Services(Long serviceId, String name, double price, List<Reservation> reservations) {
        this.serviceId = serviceId;
        this.name = name;
        this.price = price;
        this.reservations = reservations;
    }

    public Services() {

    }

    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
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