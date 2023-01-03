package com.SaeedAndEmre.CS393Project.Services;

import com.SaeedAndEmre.CS393Project.DTO.CarDTO;
import com.SaeedAndEmre.CS393Project.DTO.CreateCarDTO;
import com.SaeedAndEmre.CS393Project.DTO.TypeAndTransmissionDTO;
import com.SaeedAndEmre.CS393Project.Entities.Car;
import com.SaeedAndEmre.CS393Project.Mappers.CarMapper;
import com.SaeedAndEmre.CS393Project.Repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PutMapping;

import javax.xml.crypto.Data;
import java.util.List;
import java.util.Optional;

@Service
public class CarService {
    @Autowired
    CarRepository carRepository;


    public List<Car> findAvailableByTypeAndTransmission(String type,String transmissionType){

        return carRepository.findAvailableByTypeAndTransmission(type,
                transmissionType).get();
    }
    public List<Car> findAllRented() {
        List<Car> result= carRepository.findAllRented();
        if(result.isEmpty()){
            throw new EmptyResultDataAccessException(1);
        }
        return result;
    }

    public List<Car> findAll() {

        List<Car> cars=carRepository.findAll();
        if(cars.isEmpty()){
            throw new EmptyResultDataAccessException(1);
        }
        else{
            return cars;
        }

    }



    @Transactional(rollbackFor = {EmptyResultDataAccessException.class})
    public Boolean deleteById(long barcode) {
            Optional<Car> car = carRepository.findById(barcode);
            if(car.isPresent() ) {
                if(car.get().getStatus()!= Car.CarStatus.AVAILABLE
                        || car.get().getStatus()== Car.CarStatus.RESERVED){
                    return false;
                }
                else{
                    carRepository.deleteById(car.get().getBarcode());
                    return true;
                }
            }
            else{
                throw new EmptyResultDataAccessException(1);
            }

    }

    @Transactional(rollbackFor = {Exception.class})
    public Car save(Car car) {

        try{
            Car result=carRepository.save(car);
            return result;
        }catch (Exception e){
            throw e;
        }

    }

    @Transactional(rollbackFor = {EmptyResultDataAccessException.class})
    public Car findByBarcode(Long barcode){
        try{
            Car res=carRepository.findByBarcode(barcode);
            return res;
        }catch (EmptyResultDataAccessException e){
            throw e;
        }

    }

    @Transactional(rollbackFor = {RuntimeException.class,Exception.class})
    public void deleteAll(){
        try{
            carRepository.deleteAll();
        }
        catch (Exception e){
            throw new RuntimeException();
        }
    }



}

