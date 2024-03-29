package com.SaeedAndEmre.CS393Project.Entities;


import org.springframework.boot.context.properties.bind.DefaultValue;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Reservation {
    @Id
    private String reservationNumber;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;
    private LocalDateTime creationDate;
    private LocalDateTime pickUpDate;
    private LocalDateTime dropOffDate;
    @ManyToOne
    @JoinColumn(name = "pick_up_location_code")
    private Location pickUpLocation;
    @ManyToOne
    @JoinColumn(name = "drop_off_location_code")
    private Location dropOffLocation;
    private LocalDateTime returnDate;
    @Enumerated(EnumType.STRING)
    private ReservationStatus status = ReservationStatus.NONE; //initially should be none

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
    @ManyToMany
    private List<Equipment> equipments = new ArrayList<>();
    @ManyToMany
    private List<Services> services = new ArrayList<>();

    public List<Services> getServices() {
        return services;
    }

    public void setServices(List<Services> services) {
        this.services = services;
    }

    public enum ReservationStatus {
        ACTIVE,
        PENDING,
        CONFIRMED,
        COMPLETED,//this should allow the car to be used for another reservation
        CANCELLED,
        NONE
    }


    //Getters and setters

    public Long getReservationNumber() {
        return Long.valueOf(reservationNumber);
    }

    public void setReservationNumber(String reservationNumber) {
        this.reservationNumber = reservationNumber;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDateTime getPickUpDate() {
        return pickUpDate;
    }

    public void setPickUpDate(LocalDateTime pickUpDate) {
        this.pickUpDate = pickUpDate;
    }

    public LocalDateTime getDropOffDate() {
        return dropOffDate;
    }

    public void setDropOffDate(LocalDateTime dropOffDate) {
        this.dropOffDate = dropOffDate;
    }

    public Location getPickUpLocation() {
        return pickUpLocation;
    }

    public void setPickUpLocation(Location pickUpLocation) {
        this.pickUpLocation = pickUpLocation;
    }

    public Location getDropOffLocation() {
        return dropOffLocation;
    }

    public void setDropOffLocation(Location dropOffLocation) {
        this.dropOffLocation = dropOffLocation;
    }

    public LocalDateTime getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDateTime returnDate) {
        this.returnDate = returnDate;
    }

    public ReservationStatus getStatus() {
        return status;
    }

    public void setStatus(ReservationStatus status) {
        this.status = status;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public List<Equipment> getEquipments() {
        return equipments;
    }

    public void setEquipments(List<Equipment> equipments) {
        this.equipments = equipments;
    }
}
