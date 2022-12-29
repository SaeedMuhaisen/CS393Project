package com.SaeedAndEmre.CS393Project.DTO;

import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ReservationDTO {

    private Long barcode;
    private int dayCount;
    private Long memberId;
    private Integer pickUpLocation;
    private Integer dropOffLocation;
    private List<Long> equipments = new ArrayList<>();
    private List<Long> services = new ArrayList<>();

    public ReservationDTO(Long barcode, int dayCount, Long memberId, Integer pickUpLocation, Integer dropOffLocation, List<Long> equipments, List<Long> services) {
        this.barcode = barcode;
        this.dayCount = dayCount;
        this.memberId = memberId;
        this.pickUpLocation = pickUpLocation;
        this.dropOffLocation = dropOffLocation;
        this.equipments = equipments;
        this.services = services;
    }

    public Long getBarcode() {
        return barcode;
    }

    public void setBarcode(Long barcode) {
        this.barcode = barcode;
    }

    public int getDayCount() {
        return dayCount;
    }

    public void setDayCount(int dayCount) {
        this.dayCount = dayCount;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
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

    public List<Long> getEquipments() {
        return equipments;
    }

    public void setEquipments(List<Long> equipments) {
        this.equipments = equipments;
    }

    public List<Long> getServices() {
        return services;
    }

    public void setServices(List<Long> services) {
        this.services = services;
    }
}