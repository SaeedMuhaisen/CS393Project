package com.SaeedAndEmre.CS393Project.Entities;
import javax.persistence.*;

@Entity
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long barcode;
    private String licensePlateNumber;//TODO:int or string?
    private Integer passengerCapacity;
    private String brand;
    private String model;
    private Integer mileage;
    private String transmissionType;
    private Float dailyPrice;

    @Enumerated(EnumType.STRING)
    private CarType type;

    @Enumerated(EnumType.STRING)
    private CarStatus status;//initially should be available

    public Car(Long barcode, String licensePlateNumber, Integer passengerCapacity, String brand, String model, Integer mileage, String transmissionType, Float dailyPrice, CarType type, CarStatus status) {
        this.barcode = barcode;
        this.licensePlateNumber = licensePlateNumber;
        this.passengerCapacity = passengerCapacity;
        this.brand = brand;
        this.model = model;
        this.mileage = mileage;
        this.transmissionType = transmissionType;
        this.dailyPrice = dailyPrice;
        this.type = type;
        this.status = status;
    }

    public Car() {

    }

    public enum CarType {
        ECONOMY,
        PEOPLE_CARRIER,
        ESTATE,
        SUV,
        STANDARD,
        CONVERTIBLE,
        LUXURY;


    }

    public enum CarStatus {
        AVAILABLE,
        RESERVED,
        LOANED,
        LOST,
        BEING_SERVICED
    }

    public Long getBarcode() {
        return barcode;
    }

    public void setBarcode(Long barcode) {
        this.barcode = barcode;
    }

    public String getLicensePlateNumber() {
        return licensePlateNumber;
    }

    public void setLicensePlateNumber(String licensePlateNumber) {
        this.licensePlateNumber = licensePlateNumber;
    }

    public Integer getPassengerCapacity() {
        return passengerCapacity;
    }

    public void setPassengerCapacity(Integer passengerCapacity) {
        this.passengerCapacity = passengerCapacity;
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

    public Integer getMileage() {
        return mileage;
    }

    public void setMileage(Integer mileage) {
        this.mileage = mileage;
    }

    public String getTransmissionType() {
        return transmissionType;
    }

    public void setTransmissionType(String transmissionType) {
        this.transmissionType = transmissionType;
    }

    public Float getDailyPrice() {
        return dailyPrice;
    }

    public void setDailyPrice(Float dailyPrice) {
        this.dailyPrice = dailyPrice;
    }

    public CarType getType() {
        return type;
    }

    public void setType(CarType type) {
        this.type = type;
    }

    public CarStatus getStatus() {
        return status;
    }

    public void setStatus(CarStatus status) {
        this.status = status;
    }
}