package com.SaeedAndEmre.CS393Project.DTO;

import com.SaeedAndEmre.CS393Project.Entities.Location;

import java.time.LocalDateTime;

public class ReservationInfoDTO {
    private String reservationNumber;
    private LocalDateTime pickUpDate;
    private LocalDateTime dropOffDate;
    private Integer pickUpLocation; //Location.code
    private Integer dropOffLocation;
    private Double totalAmount;

    public ReservationInfoDTO(String reservationNumber, LocalDateTime pickUpDate, LocalDateTime dropOffDate, Integer pickUpLocation, Integer dropOffLocation, Double totalAmount) {
        this.reservationNumber = reservationNumber;
        this.pickUpDate = pickUpDate;
        this.dropOffDate = dropOffDate;
        this.pickUpLocation = pickUpLocation;
        this.dropOffLocation = dropOffLocation;
        this.totalAmount = totalAmount;
    }

    public String getReservationNumber() {
        return reservationNumber;
    }

    public void setReservationNumber(String reservationNumber) {
        this.reservationNumber = reservationNumber;
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

    public Integer getPickUpLocation() {
        return pickUpLocation;
    }

    public void setPickUpLocation(Integer pickUpLocation) {
        this.pickUpLocation = pickUpLocation;
    }

    public Integer getDropOffLocation() {
        return dropOffLocation;
    }

    public void setDropOffLocation(Integer dropOffLocation) {
        this.dropOffLocation = dropOffLocation;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }
}