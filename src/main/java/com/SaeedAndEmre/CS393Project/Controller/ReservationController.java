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
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.List;
//TODO:Do we map here or in services?

@RestController
@EnableTransactionManagement
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
    //TODO: Response status is not clear, are we sending 406 or 206 when car is not available?
    @PostMapping(value = "/reservation/new")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<ReservationInfoDTO> save(@RequestBody ReservationDTO reservationDTO) {
        try{
            ReservationInfoDTO reservationInfoDTO=reservationServices.save(reservationDTO);
            return new ResponseEntity<>(reservationInfoDTO,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }
    @PutMapping(value="reservation/{reservationId}/extraServices")
    public ResponseEntity<Boolean> addService(@RequestBody ExtraServiceDTO extraServiceDTO,@PathVariable Long reservationId){
        try{
            Boolean response=reservationServices.addService(extraServiceDTO);
            if(response){
                return new ResponseEntity<>(true, HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>(false,HttpStatus.NOT_FOUND);
            }
        }catch (EmptyResultDataAccessException e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }


    }
    @PutMapping(value="reservation/{reservationId}/extraEquipment")
    public ResponseEntity<Boolean> addEquipment(@RequestBody ExtraEquipmentDTO extraEquipmentDTO,@PathVariable Long reservationId){
        try{
            Boolean response=reservationServices.addEquipment(extraEquipmentDTO);
            if(response){
                return new ResponseEntity<>(true, HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>(false,HttpStatus.NOT_FOUND);
            }
        }catch (EmptyResultDataAccessException e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }
    @PutMapping(value="reservation/{reservationId}")
    public ResponseEntity<Boolean> cancelReservation(@PathVariable Long reservationId){
        try{
            Boolean response=reservationServices.cancelReservation(reservationId);
            if(response){
                return new ResponseEntity<>(true, HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>(false,HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }
}
