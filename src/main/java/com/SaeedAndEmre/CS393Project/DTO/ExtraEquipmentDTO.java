package com.SaeedAndEmre.CS393Project.DTO;

public class ExtraEquipmentDTO {
    private Long reservationNumber;
    private Long equipmentId;

    public ExtraEquipmentDTO(Long reservationNumber, Long equipmentId) {
        this.reservationNumber = reservationNumber;
        this.equipmentId = equipmentId;
    }

    public Long getReservationNumber() {
        return reservationNumber;
    }

    public void setReservationNumber(Long reservationNumber) {
        this.reservationNumber = reservationNumber;
    }

    public Long getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(Long equipmentId) {
        this.equipmentId = equipmentId;
    }
}