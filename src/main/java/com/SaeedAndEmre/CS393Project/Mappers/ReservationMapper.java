package com.SaeedAndEmre.CS393Project.Mappers;

import com.SaeedAndEmre.CS393Project.DTO.*;
import com.SaeedAndEmre.CS393Project.Entities.Equipment;
import com.SaeedAndEmre.CS393Project.Entities.Location;
import com.SaeedAndEmre.CS393Project.Entities.Reservation;
import com.SaeedAndEmre.CS393Project.Entities.Services;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReservationMapper {
    ReservationMapper INSTANCE= Mappers.getMapper(ReservationMapper.class);

    @Mapping(source="reservation.pickUpLocation.code",target="pickUpLocation")
    @Mapping(source="reservation.dropOffLocation.code",target="dropOffLocation")
    @Mapping(source="totalAmount",target="totalAmount")
    ReservationInfoDTO toReservationInfoDTO(Reservation reservation,double totalAmount);
}
