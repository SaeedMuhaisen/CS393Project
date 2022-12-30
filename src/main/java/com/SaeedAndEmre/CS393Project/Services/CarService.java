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

import javax.xml.crypto.Data;
import java.util.List;
import java.util.Optional;

@Service
public class CarService {
    @Autowired
    CarRepository carRepository;


    public List<CarDTO> findAvailableByTypeAndTransmission(TypeAndTransmissionDTO typeAndTransmissionDTO){
        Optional<List<Car>> cars=carRepository.findAvailableByTypeAndTransmission(typeAndTransmissionDTO.getType(),
                typeAndTransmissionDTO.getTransmissionType());
        if(!cars.isPresent()){
            throw new EmptyResultDataAccessException(1);
        }
        return CarMapper.INSTANCE.toCarDTOs(cars.get());
    }
    public List<CarDTO> findAllRented() {
        Optional<List<Car>> result= carRepository.findAllRented();
        if(!result.isPresent()){
            throw new EmptyResultDataAccessException(1);
        }
        return CarMapper.INSTANCE.toCarDTOs(result.get());
    }

    public List<CarDTO> findAll() {

        List<Car> cars=carRepository.findAll();
        if(cars.isEmpty()){
            throw new EmptyResultDataAccessException(1);
        }
        else{
            return CarMapper.INSTANCE.toCarDTOs(cars);
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

