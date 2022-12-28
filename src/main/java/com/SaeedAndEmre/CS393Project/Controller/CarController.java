package com.SaeedAndEmre.CS393Project.Controller;


import com.SaeedAndEmre.CS393Project.DTO.CarDTO;
import com.SaeedAndEmre.CS393Project.Entities.Car;
import com.SaeedAndEmre.CS393Project.Mappers.CarMapper;
import com.SaeedAndEmre.CS393Project.Services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@RestController
public class CarController {
    @Autowired
    CarService carService;

    @GetMapping(value="/cars")
    public ResponseEntity<List<CarDTO>> findAvailableByTypeAndTransmission(@RequestParam String type, @RequestParam String transmission){
        try {
            return new ResponseEntity<>
                    (CarMapper.INSTANCE.toCarDTOs
                            (carService.findAvailableByTypeAndTransmission(type, transmission))
                            , HttpStatus.OK);
        }catch (EmptyResultDataAccessException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



    @GetMapping(value="/cars/all")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<CarDTO> findAll(){
        List<Car> cars=carService.findAll();
        return CarMapper.INSTANCE.toCarDTOs(cars);
    }

    @GetMapping(value="/cars/unavailable")
    public ResponseEntity<List<CarDTO>> findAllRented(){
        try {
            List<CarDTO> cars=CarMapper.INSTANCE.toCarDTOs(carService.findAllRented());
            return new ResponseEntity<>(cars,HttpStatus.OK);
        }catch (EmptyResultDataAccessException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
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
    @PutMapping(value="/cars/new")
    public ResponseEntity<Long> save(@RequestBody CarDTO carDTO){
        try{
            CarDTO temp=CarMapper.INSTANCE.toCarDTO(carService.save(CarMapper.INSTANCE.toCar(carDTO)));
            return  new ResponseEntity<>(temp.getBarcode(),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }


}

