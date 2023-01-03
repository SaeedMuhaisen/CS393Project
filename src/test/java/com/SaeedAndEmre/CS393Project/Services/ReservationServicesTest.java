package com.SaeedAndEmre.CS393Project.Services;

import com.SaeedAndEmre.CS393Project.DTO.CreateCarDTO;
import com.SaeedAndEmre.CS393Project.Entities.Car;
import com.SaeedAndEmre.CS393Project.Entities.Equipment;
import com.SaeedAndEmre.CS393Project.Entities.Location;
import com.SaeedAndEmre.CS393Project.Entities.Services;
import com.SaeedAndEmre.CS393Project.Mappers.CarMapper;
import com.SaeedAndEmre.CS393Project.Repositories.EquipmentRepository;
import com.SaeedAndEmre.CS393Project.Repositories.LocationRepository;
import com.SaeedAndEmre.CS393Project.Repositories.ServicesRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ReservationServicesTest {
    @Autowired
    CarService carService;
    @Autowired
    ReservationServices reservationServices;
    @Autowired
    ServicesRepository servicesRepository;
    @Autowired
    EquipmentRepository equipmentRepository;
    @Autowired
    LocationRepository locationRepository;


    @BeforeEach
    public void insertingCars(){
        Car test=new Car();
        test.setStatus(Car.CarStatus.AVAILABLE);
        test.setType(Car.CarType.STANDARD);
        test.setPassengerCapacity(4);
        test.setModel("2004");
        test.setTransmissionType("AUTOMATIC");
        test.setLicensePlateNumber("abc");
        test.setDailyPrice(15.5f);
        test.setMileage(0);
        test.setBrand("BMW");
        test.setBarcode(carService.save(test).getBarcode());
        Car result=carService.findByBarcode(test.getBarcode());
        assertEquals(test.getStatus(),result.getStatus());
        assertEquals(test.getType(),result.getType());

        Equipment equipment=new Equipment();

        equipment.setName("snow tires");
        equipment.setPrice(10);
        equipment.setEquipmentId(equipmentRepository.save(equipment).getEquipmentId());

        Equipment equipment1=new Equipment();
        equipment1.setName("Child Seat");
        equipment1.setPrice(20);
        equipment1.setEquipmentId(equipmentRepository.save(equipment1).getEquipmentId());

        Services service1=new Services();
        service1.setName("Additional Driver");
        service1.setPrice(100);
        service1.setServiceId(servicesRepository.save(service1).getServiceId());

        Services service2=new Services();
        service2.setName("Towing Service");
        service2.setPrice(200);
        service2.setServiceId(servicesRepository.save(service2).getServiceId());

        Location location=new Location();
        location.setName("Istanbul Airport");
        location.setAddress("Istanbul Anadolu");
        location.setCode(locationRepository.save(location).getCode());

        Location location1=new Location();
        location1.setName("OzU");
        location1.setAddress("Cekmekoy");
        location.setCode(locationRepository.save(location1).getCode());
    }

    @Test
    void save() {


    }

    @Test
    void addService() {
    }

    @Test
    void addEquipment() {
    }

    @Test
    void cancelReservation() {
    }
}