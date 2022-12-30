package com.SaeedAndEmre.CS393Project.Services;

import com.SaeedAndEmre.CS393Project.DTO.CarDTO;
import com.SaeedAndEmre.CS393Project.DTO.TypeAndTransmissionDTO;
import com.SaeedAndEmre.CS393Project.Entities.Car;
import com.SaeedAndEmre.CS393Project.Mappers.CarMapper;
import com.SaeedAndEmre.CS393Project.Repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {
    @Autowired
    CarRepository carRepository;


    public List<CarDTO> findAvailableByTypeAndTransmission(TypeAndTransmissionDTO typeAndTransmissionDTO) {
        List<Car> cars=carRepository.findAvailableByTypeAndTransmission(typeAndTransmissionDTO.getType(),
                typeAndTransmissionDTO.getTransmissionType());
        List<CarDTO> carDTOS=CarMapper.INSTANCE.toCarDTOs(cars);
        return carDTOS;
    }
    public List<CarDTO> findAllRented() {
        return CarMapper.INSTANCE.toCarDTOs(carRepository.findAllRented());
    }

    public List<Car> findAll() {
        return carRepository.findAll();
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
    public Car findByBarcode(Long barcode){
        return carRepository.findByBarcode(barcode);
    }
}

