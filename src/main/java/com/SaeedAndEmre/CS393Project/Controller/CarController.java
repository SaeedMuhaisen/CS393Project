package com.SaeedAndEmre.CS393Project.Controller;


import com.SaeedAndEmre.CS393Project.DTO.CarDTO;
import com.SaeedAndEmre.CS393Project.DTO.CreateCarDTO;
import com.SaeedAndEmre.CS393Project.DTO.TypeAndTransmissionDTO;
import com.SaeedAndEmre.CS393Project.Entities.Car;
import com.SaeedAndEmre.CS393Project.Mappers.CarMapper;
import com.SaeedAndEmre.CS393Project.Services.CarService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import javax.validation.constraints.NotEmpty;
import java.util.List;
//todo: REQUEST BODY, in findBytypetransmision function springboot @Requestbody doesnt work only swagger
// does, and on save method springboot request body works but swagger doesn't
@RestController
public class CarController {
    @Autowired
    CarService carService;

    //DONE
    @RequestMapping(value="/cars",method= RequestMethod.GET)
    public ResponseEntity<List<CarDTO>> findAvailableByTypeAndTransmission(@io.swagger.v3.oas.annotations.parameters.RequestBody TypeAndTransmissionDTO typeAndTransmissionDTO){
        try {
            List<CarDTO> carDTOS = carService.findAvailableByTypeAndTransmission(typeAndTransmissionDTO);
            return new ResponseEntity<>(carDTOS,HttpStatus.OK);
        }
       catch(EmptyResultDataAccessException e) {
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }
    }
    //DONE
    @GetMapping(value="/cars/unavailable")
    public ResponseEntity<List<CarDTO>> findAllRented() {

        try{
            List<CarDTO> carDTOS = carService.findAllRented();
            return new ResponseEntity<>(carDTOS, HttpStatus.OK);
        } catch(EmptyResultDataAccessException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    //DONE
    @DeleteMapping(value="/cars/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable (name="id",required = false) Long barcode){
        try {
            Boolean response = carService.deleteById(barcode);
            if(response){
                return new ResponseEntity(true, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
            }
        }catch (Exception e){
            if(e.getClass()==EmptyResultDataAccessException.class) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            else{
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }
    //DONE
    @GetMapping(value="/cars/all")
    public ResponseEntity<List<CarDTO>> findAll(){
        try {
            List<CarDTO> cars = carService.findAll();
            return new ResponseEntity<>(cars,HttpStatus.OK);
        }
        catch(EmptyResultDataAccessException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
    //TODO:What type of exceptions can happen here?
    @RequestMapping(value="/cars/new",method = RequestMethod.POST)
    public ResponseEntity<CreateCarDTO> save(@RequestBody CreateCarDTO createCarDTO){
        try {
            CreateCarDTO response=carService.save(createCarDTO);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }


    //DONE
    @PutMapping(value="/cars/{id}")
    public ResponseEntity<CreateCarDTO> update(@RequestBody CreateCarDTO createCarDTO,@PathVariable(value = "id") int id ){
            carService.save(createCarDTO);
            return new ResponseEntity<>(createCarDTO,HttpStatus.OK);
    }


    //EXTRA METHOD BUT EXCEPTION NOT WORKING
    @GetMapping(value="/cars/{id}")
    public ResponseEntity<CreateCarDTO> findByBarcode(@RequestParam(name="id",required = false) Long barcode) {
            try {
                CreateCarDTO createCarDTO = carService.findByBarcode(barcode);
                return new ResponseEntity(createCarDTO, HttpStatus.OK);
            }catch(Exception e){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
    }
}

