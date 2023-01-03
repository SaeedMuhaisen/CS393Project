package com.SaeedAndEmre.CS393Project.Mappers;

import com.SaeedAndEmre.CS393Project.DTO.EquipmentDTO;
import com.SaeedAndEmre.CS393Project.Entities.Equipment;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EquipmentMapper {
    EquipmentMapper INSTANCE= Mappers.getMapper(EquipmentMapper.class);

    List<EquipmentDTO> toEquipmentDTOS(List<Equipment> equipments);

    Equipment toEquipment(EquipmentDTO equipmentDTO);
    EquipmentDTO toEquipmentDTO(Equipment equipment);
}
