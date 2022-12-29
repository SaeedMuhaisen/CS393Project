package com.SaeedAndEmre.CS393Project.DTO;

public class EquipmentDTO {
    private Long equipmentId;
    private String name;
    private double price;

    public EquipmentDTO(Long equipmentId, String name, double price) {
        this.equipmentId = equipmentId;
        this.name = name;
        this.price = price;
    }
    public Long getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(Long equipmentId) {
        this.equipmentId = equipmentId;
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
}
