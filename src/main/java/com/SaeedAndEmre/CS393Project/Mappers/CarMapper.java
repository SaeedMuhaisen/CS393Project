package com.SaeedAndEmre.CS393Project.Mappers;


import com.SaeedAndEmre.CS393Project.DTO.CarDTO;
import com.SaeedAndEmre.CS393Project.DTO.CreateCarDTO;
import com.SaeedAndEmre.CS393Project.DTO.RentedCarsDTO;
import com.SaeedAndEmre.CS393Project.Entities.Car;
import com.SaeedAndEmre.CS393Project.Entities.Reservation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CarMapper {
    CarMapper INSTANCE= Mappers.getMapper(CarMapper.class);
    CarDTO toCarDTO(Car car);
    Car toCar(CarDTO carDTO);
    List<CarDTO> toCarDTOs(List<Car> cars);

    CreateCarDTO fromCarToCreateCarDTO(Car car);
    Car fromCreateCarDTOtoCar(CreateCarDTO createCarDTO);

    List<CreateCarDTO> fromCarsToCreateCarsDTO(List<Car> cars);

    //List<RentedCarsDTO> toRentedCarsDTOs(List<Car> cars, List<Reservation> reservation);
    @Mapping(source="reservation.reservationNumber",target="reservationNumber")
    @Mapping(source="reservation.member.name",target="memberName")
    @Mapping(source="reservation.dropOffDate",target="dropOffDate")
    @Mapping(source="reservation.dropOffLocation.code",target="dropOffLocation")
    RentedCarsDTO toRentedCarDTO(Car car,Reservation reservation);
}