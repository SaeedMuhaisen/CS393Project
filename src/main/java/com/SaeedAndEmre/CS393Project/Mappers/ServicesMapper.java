package com.SaeedAndEmre.CS393Project.Mappers;

import com.SaeedAndEmre.CS393Project.DTO.EquipmentDTO;
import com.SaeedAndEmre.CS393Project.DTO.ServicesDTO;
import com.SaeedAndEmre.CS393Project.Entities.Equipment;
import com.SaeedAndEmre.CS393Project.Entities.Services;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ServicesMapper {
    ServicesMapper INSTANCE= Mappers.getMapper(ServicesMapper.class);

    List<ServicesDTO> toServicesDTOS(List<Services> services);

    Services toService(ServicesDTO servicesDTO);
    ServicesDTO toServiceDTO(Services services);
}
