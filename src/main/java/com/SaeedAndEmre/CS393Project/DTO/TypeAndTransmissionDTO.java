package com.SaeedAndEmre.CS393Project.DTO;

public class TypeAndTransmissionDTO {
    private String type;
    private String transmissionType;

    public TypeAndTransmissionDTO(String type, String transmissionType) {
        this.type = type;
        this.transmissionType = transmissionType;
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
}
