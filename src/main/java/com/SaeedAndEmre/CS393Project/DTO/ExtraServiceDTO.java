package com.SaeedAndEmre.CS393Project.DTO;

public class ExtraServiceDTO {
    private Long reservationNumber;
    private Long serviceId;

    public ExtraServiceDTO(Long reservationNumber, Long serviceId) {
        this.reservationNumber = reservationNumber;
        this.serviceId = serviceId;
    }

    public Long getReservationNumber() {
        return reservationNumber;
    }

    public void setReservationNumber(Long reservationNumber) {
        this.reservationNumber = reservationNumber;
    }

    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }
}
