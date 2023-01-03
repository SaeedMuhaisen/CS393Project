package com.SaeedAndEmre.CS393Project.Mappers;

import com.SaeedAndEmre.CS393Project.DTO.LocationDTO;
import com.SaeedAndEmre.CS393Project.Entities.Location;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LocationMapper {
    LocationMapper INSTANCE= Mappers.getMapper(LocationMapper.class);

    Location toLocation(LocationDTO locationDTO);
    LocationDTO toLocationDTO(Location location);

    List<LocationDTO> toLocationDTOS(List<Location> locations);
}
