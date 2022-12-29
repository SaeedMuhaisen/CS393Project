package com.SaeedAndEmre.CS393Project.DTO;

public class ServicesDTO {
    private Long serviceId;
    private String name;
    private double price;

    public ServicesDTO(Long serviceId, String name, double price) {
        this.serviceId = serviceId;
        this.name = name;
        this.price = price;
    }

    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
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
