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




    public Boolean deleteById(long barcode) {
        try {
            Car car = carRepository.findById(barcode);
            carRepository.deleteById(barcode);
            return true;
        }catch (EmptyResultDataAccessException e){
            return false;
        }

    }

    public CreateCarDTO save(CreateCarDTO createCarDTO) {
        Car car=CarMapper.INSTANCE.fromCreateCarDTOtoCar(createCarDTO);
        return CarMapper.INSTANCE.fromCarToCreateCarDTO(carRepository.save(car));
    }
    public CreateCarDTO update(CreateCarDTO createCarDTO){
        Car car=CarMapper.INSTANCE.fromCreateCarDTOtoCar(createCarDTO);
        return CarMapper.INSTANCE.fromCarToCreateCarDTO(carRepository.update(car));
    }
    public CreateCarDTO findByBarcode(Long barcode){
        try{
            CreateCarDTO res=CarMapper.INSTANCE.fromCarToCreateCarDTO(carRepository.findByBarcode(barcode));
            return res;
        }catch (EmptyResultDataAccessException e){
            System.out.println("CAR WAS NOT FOUND!!!!");
            throw e;
        }

    }
}

