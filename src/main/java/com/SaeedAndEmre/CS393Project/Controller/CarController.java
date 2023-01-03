package com.SaeedAndEmre.CS393Project.Controller;


import com.SaeedAndEmre.CS393Project.DTO.CarDTO;
import com.SaeedAndEmre.CS393Project.DTO.CreateCarDTO;
import com.SaeedAndEmre.CS393Project.DTO.RentedCarsDTO;
import com.SaeedAndEmre.CS393Project.Entities.Car;
import com.SaeedAndEmre.CS393Project.Entities.Reservation;
import com.SaeedAndEmre.CS393Project.Mappers.CarMapper;
import com.SaeedAndEmre.CS393Project.Services.CarService;

import com.SaeedAndEmre.CS393Project.Services.ReservationServices;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//todo: REQUEST BODY, in findBytypetransmision function springboot @Requestbody doesnt work only swagger
// does, and on save method springboot request body works but swagger doesn't
@RestController
public class CarController {
    @Autowired
    CarService carService;
    @Autowired
    ReservationServices reservationServices;

    //DONE
    @RequestMapping(value="/cars/TypeAndTransmission",method= RequestMethod.GET)
    @Operation(summary = "Find available cars by type and transmission type",
            description = "insert type and transmission type")

    public ResponseEntity<List<CarDTO>> findAvailableByTypeAndTransmission(@RequestParam String type,@RequestParam String transmissionType){
            List<Car> cars= carService.findAvailableByTypeAndTransmission
                    (type,transmissionType);
            if(!cars.isEmpty()){
                return new ResponseEntity<>(CarMapper.INSTANCE.toCarDTOs(cars),HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

    }
    //DONE
    @GetMapping(value="/cars/unavailable")
    @Operation(summary = "Find All rented cars")
    public ResponseEntity<List<RentedCarsDTO>> findAllRented() {

        try {
            List<RentedCarsDTO> rentedCarsDTOs = new ArrayList<>();
            List<Car> cars = carService.findAllRented();
            for (Car car : cars) {
                try {
                    Reservation reservation = reservationServices.findByIdAndActive(car.getBarcode());
                    rentedCarsDTOs.add(CarMapper.INSTANCE.toRentedCarDTO(car, reservation));
                } catch (RuntimeException e) {
                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }
            return new ResponseEntity<>(rentedCarsDTOs, HttpStatus.OK);
        } catch (EmptyResultDataAccessException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    //DONE
    @DeleteMapping(value="/cars/{barcode}")
    @Operation(summary = "Delete a car by inserting its barcode",
            description = "insert barcode")
    public ResponseEntity<Boolean> deleteById(@PathVariable (name="barcode") Long barcode) {
        try {
            Boolean response = carService.deleteById(barcode);
            if (response) {
                return new ResponseEntity(true, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
            }
        } catch (EmptyResultDataAccessException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //DONE
    @Operation(summary = "List all cars")
    @GetMapping(value="/cars")
    public ResponseEntity<List<CreateCarDTO>> findAll(){
        try {
            List<CreateCarDTO> cars = CarMapper.INSTANCE.fromCarsToCreateCarsDTO(carService.findAll());
            return new ResponseEntity<>(cars,HttpStatus.OK);
        }
        catch(EmptyResultDataAccessException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
    @Operation(summary = "Add a new car to your inventory",
            description = "insert Car details below")
    @RequestMapping(value="/cars/new",method = RequestMethod.POST)
    public ResponseEntity<CreateCarDTO> save(@RequestBody CreateCarDTO createCarDTO){
        try {
            CreateCarDTO response=CarMapper.INSTANCE.fromCarToCreateCarDTO(carService.save(CarMapper.INSTANCE.fromCreateCarDTOtoCar(createCarDTO)));
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }


    //DONE
    @PutMapping(value="/cars/{barcode}")
    @Operation(summary = "Update an already existing car",
            description = "insert all details new and old of the car you are updating ")
    public ResponseEntity<CreateCarDTO> update(@RequestBody CreateCarDTO createCarDTO,@PathVariable(value = "barcode") int id ){
        try {
            carService.save(CarMapper.INSTANCE.fromCreateCarDTOtoCar(createCarDTO));
            return new ResponseEntity<>(createCarDTO, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @Operation(summary = "Find a car by barcode",
            description = "insert Car details below")
    @GetMapping(value="/cars/{barcode}")
    public ResponseEntity<CreateCarDTO> findByBarcode(@RequestParam(name="barcode",defaultValue = "0") Long barcode) {
            try {
                CreateCarDTO createCarDTO = CarMapper.INSTANCE.fromCarToCreateCarDTO(carService.findByBarcode(barcode));
                return new ResponseEntity(createCarDTO, HttpStatus.OK);
            }catch(EmptyResultDataAccessException e){
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
    }
}

