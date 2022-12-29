package com.SaeedAndEmre.CS393Project.Controller;

import com.SaeedAndEmre.CS393Project.DTO.*;
import com.SaeedAndEmre.CS393Project.Entities.Car;
import com.SaeedAndEmre.CS393Project.Entities.Equipment;
import com.SaeedAndEmre.CS393Project.Entities.Member;
import com.SaeedAndEmre.CS393Project.Entities.Reservation;
import com.SaeedAndEmre.CS393Project.Mappers.*;
import com.SaeedAndEmre.CS393Project.Repositories.CarRepository;
import com.SaeedAndEmre.CS393Project.Repositories.EquipmentRepository;
import com.SaeedAndEmre.CS393Project.Repositories.MemberRepository;
import com.SaeedAndEmre.CS393Project.Repositories.ServicesRepository;
import com.SaeedAndEmre.CS393Project.Services.CarService;
import com.SaeedAndEmre.CS393Project.Services.ReservationServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//TODO:Do we map here or in services?

@RestController
public class ReservationController {
    @Autowired
    ReservationServices reservationServices;
    @Autowired
    CarRepository carRepository;
    @Autowired
    EquipmentRepository equipmentRepository;
    @Autowired
    ServicesRepository servicesRepository;
    @Autowired
    MemberRepository memberRepository;
    //TODO: are we taking list on ids of equipments or names or what exactly?
    @PostMapping(value = "/reservation/new")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<ReservationInfoDTO> save(@RequestBody ReservationDTO reservationDTO){
        System.out.println("hello from controller");
        ReservationInfoDTO reservationInfoDTO=reservationServices.save(reservationDTO);
        return new ResponseEntity<>(reservationInfoDTO,HttpStatus.OK);
    }

}
