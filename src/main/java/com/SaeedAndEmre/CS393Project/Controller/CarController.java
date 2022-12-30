package com.SaeedAndEmre.CS393Project.Controller;


import com.SaeedAndEmre.CS393Project.DTO.CarDTO;
import com.SaeedAndEmre.CS393Project.DTO.CreateCarDTO;
import com.SaeedAndEmre.CS393Project.DTO.TypeAndTransmissionDTO;
import com.SaeedAndEmre.CS393Project.Entities.Car;
import com.SaeedAndEmre.CS393Project.Mappers.CarMapper;
import com.SaeedAndEmre.CS393Project.Services.CarService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

            List<CarDTO> carDTOS = carService.findAvailableByTypeAndTransmission(typeAndTransmissionDTO);
        if(!carDTOS.isEmpty()){
            return new ResponseEntity<>(carDTOS,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
    //DONE
    @GetMapping(value="/cars/unavailable")
    public ResponseEntity<List<CarDTO>> findAllRented() {
        List<CarDTO> carDTOS = carService.findAllRented();
        if (!carDTOS.isEmpty()) {
            return new ResponseEntity<>(carDTOS, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    //DONE
    @GetMapping(value="/cars/all")
    public List<CarDTO> findAll(){
        List<Car> cars=carService.findAll();
        return CarMapper.INSTANCE.toCarDTOs(cars);
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
    //DONE
    @DeleteMapping(value="/cars/{id}")
    public ResponseEntity<Boolean> deleteById(@RequestParam(name="id",required = false) Long barcode) {
        Boolean response = carService.deleteById(barcode);
        return new ResponseEntity(response, HttpStatus.OK);

    }
}

