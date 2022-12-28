package com.SaeedAndEmre.CS393Project.Services;

import com.SaeedAndEmre.CS393Project.DTO.CarDTO;
import com.SaeedAndEmre.CS393Project.Entities.Car;
import com.SaeedAndEmre.CS393Project.Repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {
    @Autowired
    CarRepository carRepository;


    public List<Car> findAvailableByTypeAndTransmission(String type, String transmission) {
        return carRepository.findAvailableByTypeAndTransmission(type, transmission);
    }

    public List<Car> findAll() {
        return carRepository.findAll();
    }

    public List<Car> findAllRented() {
        return carRepository.findAllRented();
    }

    public void update(Car car) {
        carRepository.save(car);

    }

    public Boolean deleteById(long barcode) {
        Car car = carRepository.findById(barcode);
            carRepository.deleteById(barcode);
            return true;
    }

    public Car save(Car car) {
        return carRepository.save(car);
    }
}

