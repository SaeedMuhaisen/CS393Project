package com.SaeedAndEmre.CS393Project.Controller;


import com.SaeedAndEmre.CS393Project.DTO.CarDTO;
import com.SaeedAndEmre.CS393Project.DTO.TypeAndTransmissionDTO;
import com.SaeedAndEmre.CS393Project.Entities.Car;
import com.SaeedAndEmre.CS393Project.Mappers.CarMapper;
import com.SaeedAndEmre.CS393Project.Services.CarService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CarController {
    @Autowired
    CarService carService;

    //DONe
    @RequestMapping(value="/cars",method= RequestMethod.GET)
    public ResponseEntity<List<CarDTO>> findAvailableByTypeAndTransmission(@RequestBody TypeAndTransmissionDTO typeAndTransmissionDTO){

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

    @PutMapping(value="/cars/new")
    public ResponseEntity<Long> save(@RequestBody CarDTO carDTO){

        return null;
    }


    @PutMapping(value="/cars/{id}")
    public ResponseEntity update(@RequestBody CarDTO carDTO){

            System.out.println(carDTO.getBarcode());
            carService.update(CarMapper.INSTANCE.toCar(carDTO));
            return new ResponseEntity<>(HttpStatus.OK);


    }
    @DeleteMapping(value="/cars/{id}")
    public ResponseEntity<Boolean> deleteById(@RequestParam(name="id",required = false) Long barcode){
        try{
            return new ResponseEntity(carService.deleteById(barcode),HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity(false,HttpStatus.NOT_FOUND);
        }
    }


}

