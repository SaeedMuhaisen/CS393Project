package com.SaeedAndEmre.CS393Project.DTO;

import com.fasterxml.jackson.annotation.JsonGetter;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;


public class ReservationDTO {

    private Long barcode;
    private int dayCount;
    private Long memberId;
    private Integer pickUpLocation;
    private Integer dropOffLocation;
    private Set<Long> equipments;
    private Set<Long> services;

    public ReservationDTO(Long barcode, int dayCount, Long memberId, Integer pickUpLocation, Integer dropOffLocation, Set<Long> equipments, Set<Long> services) {
        this.barcode = barcode;
        this.dayCount = dayCount;
        this.memberId = memberId;
        this.pickUpLocation = pickUpLocation;
        this.dropOffLocation = dropOffLocation;
        this.equipments = equipments;
        this.services = services;
    }

    public ReservationDTO() {

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

    public Set<Long> getEquipments() {
        return equipments;
    }

    public void setEquipments(Set<Long> equipments) {
        this.equipments = equipments;
    }

    public Set<Long> getServices() {
        return services;
    }

    public void setServices(Set<Long> services) {
        this.services = services;
    }
}