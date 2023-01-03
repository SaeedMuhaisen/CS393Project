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
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.*;

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
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.PARTIAL_CONTENT);
        }
    }
    @PutMapping(value="reservation/{reservationId}/extraServices")
    public ResponseEntity<Boolean> addService(@RequestParam Long serviceId,@PathVariable(value="reservationId") Long reservationId){
        try{
            Boolean response=reservationServices.addService(serviceId,reservationId);
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
    public ResponseEntity<Boolean> addEquipment(@RequestParam(defaultValue = "0") Long equipmentId,@PathVariable(value="reservationId") Long reservationId){
        try{
            Boolean response=reservationServices.addEquipment(equipmentId,reservationId);
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
    @PutMapping(value="reservation/{reservationId}/cancel")
    public ResponseEntity<Boolean> cancelReservation(@PathVariable (value="reservationId") Long reservationId){
        try{
            Boolean response=reservationServices.cancelReservation(reservationId);
            if(response){
                return new ResponseEntity<>(true, HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>(false,HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            return new ResponseEntity<>(false,HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @PutMapping(value="reservation/{reservationId}/finish")
    public ResponseEntity<Boolean> finsihReservation(@PathVariable(value = "reservationId") Long reservationId) {
        try {
            reservationServices.finishReservation(reservationId);
            return new ResponseEntity<>(true, HttpStatus.OK);

        } catch (EmptyResultDataAccessException e) {
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);

        } catch (Exception e) {
            return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
