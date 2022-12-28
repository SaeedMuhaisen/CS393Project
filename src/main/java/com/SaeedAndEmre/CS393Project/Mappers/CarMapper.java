package com.SaeedAndEmre.CS393Project.Mappers;


import com.SaeedAndEmre.CS393Project.DTO.CarDTO;
import com.SaeedAndEmre.CS393Project.Entities.Car;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CarMapper {
    CarMapper INSTANCE= Mappers.getMapper(CarMapper.class);
    CarDTO toCarDTO(Car car);
    Car toCar(CarDTO carDTO);

    List<CarDTO> toCarDTOs(List<Car> cars);
    List<Car> toCars(List<CarDTO> CarDTO);


}