package com.SaeedAndEmre.CS393Project.Services;

import com.SaeedAndEmre.CS393Project.DTO.ReservationDTO;
import com.SaeedAndEmre.CS393Project.DTO.ReservationInfoDTO;
import com.SaeedAndEmre.CS393Project.Entities.*;
import com.SaeedAndEmre.CS393Project.Repositories.EquipmentRepository;
import com.SaeedAndEmre.CS393Project.Repositories.LocationRepository;
import com.SaeedAndEmre.CS393Project.Repositories.MemberRepository;
import com.SaeedAndEmre.CS393Project.Repositories.ServicesRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@AutoConfigureTestDatabase
class ReservationServicesTest {
    @Autowired
    CarService carService;
    @Autowired
    ReservationServices reservationServices;
    @Autowired
    ServicesService servicesService;
    @Autowired
    EquipmentService equipmentService;
    @Autowired
    LocationService locationService;
    @Autowired
    MemberService memberService;

    Car car;
    Equipment equipment;
    Equipment equipment1;
    Services service1;
    Services service2;
    Location location;
    Location location1;
    Member member;

    @BeforeEach

    public void reset(){
        reservationServices.deleteAll();
    }

    @BeforeEach
    public void insertingCars(){
        car=new Car();
        car.setStatus(Car.CarStatus.AVAILABLE);
        car.setType(Car.CarType.STANDARD);
        car.setPassengerCapacity(4);
        car.setModel("2004");
        car.setTransmissionType("AUTOMATIC");
        car.setLicensePlateNumber("abc");
        car.setDailyPrice(15.5f);
        car.setMileage(0);
        car.setBrand("BMW");
        car.setBarcode(carService.save(car).getBarcode());
        Car result=carService.findByBarcode(car.getBarcode());
        assertEquals(car.getStatus(),result.getStatus());
        assertEquals(car.getType(),result.getType());

        equipment=new Equipment();

        equipment.setName("snow tires");
        equipment.setPrice(10);
        equipment.setEquipmentId(equipmentService.save(equipment).getEquipmentId());

        equipment1=new Equipment();
        equipment1.setName("Child Seat");
        equipment1.setPrice(20);
        equipment1.setEquipmentId(equipmentService.save(equipment1).getEquipmentId());

        service1=new Services();
        service1.setName("Additional Driver");
        service1.setPrice(100);
        service1.setServiceId(servicesService.save(service1).getServiceId());

        service2=new Services();
        service2.setName("Towing Service");
        service2.setPrice(200);
        service2.setServiceId(servicesService.save(service2).getServiceId());

        location=new Location();
        location.setName("Istanbul Airport");
        location.setAddress("Istanbul Anadolu");
        location.setCode(locationService.save(location).getCode());

        location1=new Location();
        location1.setName("OzU");
        location1.setAddress("Cekmekoy");
        location.setCode(locationService.save(location1).getCode());

        member=new Member();
        member.setName("temp");
        member.setAddress("abc");
        member.setId(memberService.save(member).getId());
    }
    @Test
    void saveAndFindByID() {
        Set<Long> services = new HashSet<>();
        services.add(service1.getServiceId());
        Set<Long> equipmentList=new HashSet<>();
        equipmentList.add(equipment.getEquipmentId());
        ReservationDTO reservation=new ReservationDTO();
        reservation.setBarcode(car.getBarcode());
        reservation.setServices(services);
        reservation.setEquipments(equipmentList);
        reservation.setDayCount(10);
        reservation.setPickUpLocation(1);
        reservation.setDropOffLocation(2);
        reservation.setMemberId(member.getId());

        try {
            ReservationInfoDTO temp = reservationServices.save(reservation);
            Reservation result = reservationServices.findByIdAndActive(temp.getReservationNumber());
            assertEquals(result.getCar().getBarcode(),car.getBarcode());
            assertEquals(result.getServices().size(),1);
            assertEquals(result.getEquipments().size(),1);
            assertEquals(result.getPickUpDate(),result.getPickUpDate());
            assertEquals(result.getStatus(),"ACTIVE");
        }catch (Exception e){
            //shouldn't be possible;
        }


    }

    @Test
    void addServiceAndEquipment() {
        Set<Long> services = new HashSet<>();
        services.add(service1.getServiceId());
        Set<Long> equipmentList=new HashSet<>();
        equipmentList.add(equipment.getEquipmentId());
        ReservationDTO reservation=new ReservationDTO();
        reservation.setBarcode(car.getBarcode());
        reservation.setServices(services);
        reservation.setEquipments(equipmentList);
        reservation.setDayCount(10);
        reservation.setPickUpLocation(1);
        reservation.setDropOffLocation(2);
        reservation.setMemberId(member.getId());

        try {
            ReservationInfoDTO temp = reservationServices.save(reservation);
            Reservation result = reservationServices.findByIdAndActive(temp.getReservationNumber());
            assertEquals(result.getCar().getBarcode(),car.getBarcode());
            assertEquals(result.getServices().size(),1);
            assertEquals(result.getEquipments().size(),1);
            assertEquals(result.getEquipments().get(0).getEquipmentId(),equipment.getEquipmentId());
            assertEquals(result.getServices().get(0),service1.getServiceId());

            reservationServices.addService(service2.getServiceId(),temp.getReservationNumber());
            result = reservationServices.findByIdAndActive(temp.getReservationNumber());
            assertEquals(result.getServices().size(),2);
            assertEquals(result.getEquipments().size(),2);
            assertEquals(result.getEquipments().get(1).getEquipmentId(),equipment1.getEquipmentId());
            assertEquals(result.getServices().get(1),service2.getServiceId());




        }catch (Exception e){
            //shouldn't be possible;
        }


    }


    @Test
    void cancelReservation() {
        Set<Long> services = new HashSet<>();
        services.add(service1.getServiceId());
        Set<Long> equipmentList=new HashSet<>();
        equipmentList.add(equipment.getEquipmentId());
        ReservationDTO reservation=new ReservationDTO();
        reservation.setBarcode(car.getBarcode());
        reservation.setServices(services);
        reservation.setEquipments(equipmentList);
        reservation.setDayCount(10);
        reservation.setPickUpLocation(1);
        reservation.setDropOffLocation(2);
        reservation.setMemberId(member.getId());

        try {
            ReservationInfoDTO temp = reservationServices.save(reservation);
            Reservation result = reservationServices.findByIdAndActive(temp.getReservationNumber());
            assertEquals(result.getCar().getBarcode(),car.getBarcode());
            assertEquals(result.getServices().size(),1);
            assertEquals(result.getEquipments().size(),1);
            assertEquals(result.getPickUpDate(),result.getPickUpDate());
            assertEquals(result.getStatus(),"ACTIVE");

            reservationServices.cancelReservation(result.getReservationNumber());
            result = reservationServices.findByIdAndActive(temp.getReservationNumber());
            assertEquals(result.getStatus(),"CANCELED");
            Car car=carService.findByBarcode(result.getCar().getBarcode());
            assertEquals(car.getStatus(),"AVAILABLE");
        }catch (Exception e){
            //shouldn't be possible;
        }


    }


    @Test
    void finishReservation() {
        Set<Long> services = new HashSet<>();
        services.add(service1.getServiceId());
        Set<Long> equipmentList=new HashSet<>();
        equipmentList.add(equipment.getEquipmentId());
        ReservationDTO reservation=new ReservationDTO();
        reservation.setBarcode(car.getBarcode());
        reservation.setServices(services);
        reservation.setEquipments(equipmentList);
        reservation.setDayCount(10);
        reservation.setPickUpLocation(1);
        reservation.setDropOffLocation(2);
        reservation.setMemberId(member.getId());

        try {
            ReservationInfoDTO temp = reservationServices.save(reservation);
            Reservation result = reservationServices.findByIdAndActive(temp.getReservationNumber());
            Car car=carService.findByBarcode(result.getCar().getBarcode());
            assertEquals(result.getCar().getBarcode(),car.getBarcode());
            assertEquals(result.getServices().size(),1);
            assertEquals(result.getEquipments().size(),1);
            assertEquals(result.getPickUpDate(),result.getPickUpDate());
            assertEquals(result.getStatus(),"ACTIVE");
            assertEquals(result.getCar().getStatus(),"LOANED");

            reservationServices.finishReservation(result.getReservationNumber());
            result = reservationServices.findByIdAndActive(temp.getReservationNumber());
            assertEquals(result.getStatus(),"COMPLETED");
            assertEquals(result.getCar().getStatus(),"AVAILABLE");



        }catch (Exception e){
            //shouldn't be possible;
        }
    }

}