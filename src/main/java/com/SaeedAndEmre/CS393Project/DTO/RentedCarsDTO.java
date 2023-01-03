package com.SaeedAndEmre.CS393Project.DTO;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class RentedCarsDTO {
    private String brand;
    private String model;
    private String type;
    private String transmissionType;
    private Long barcode;
    private Long reservationNumber;
    private String memberName;
    private LocalDateTime dropOffDate;
    private Integer dropOffLocation;
    private Long reservationDayCount;

    public RentedCarsDTO(String brand, String model, String type, String transmissionType, Long barcode, Long reservationNumber, String memberName, LocalDateTime dropOffDate, Integer dropOffLocation) {
        this.brand = brand;
        this.model = model;
        this.type = type;
        this.transmissionType = transmissionType;
        this.barcode = barcode;
        this.reservationNumber = reservationNumber;
        this.memberName = memberName;
        this.dropOffDate = dropOffDate;
        this.dropOffLocation = dropOffLocation;
        this.reservationDayCount = ChronoUnit.DAYS.between( java.time.LocalDateTime.now(),dropOffDate);
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTransmissionType() {
        return transmissionType;
    }

    public void setTransmissionType(String transmissionType) {
        this.transmissionType = transmissionType;
    }

    public Long getBarcode() {
        return barcode;
    }

    public void setBarcode(Long barcode) {
        this.barcode = barcode;
    }

    public Long getReservationNumber() {
        return reservationNumber;
    }

    public void setReservationNumber(Long reservationNumber) {
        this.reservationNumber = reservationNumber;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public LocalDateTime getDropOffDate() {
        return dropOffDate;
    }

    public void setDropOffDate(LocalDateTime dropOffDate) {
        this.dropOffDate = dropOffDate;
    }

    public Integer getDropOffLocation() {
        return dropOffLocation;
    }

    public void setDropOffLocation(Integer dropOffLocation) {
        this.dropOffLocation = dropOffLocation;
    }

    public Long getReservationDayCount() {
        return reservationDayCount;
    }

    public void setReservationDayCount(Long reservationDayCount) {
        this.reservationDayCount = reservationDayCount;
    }
}
