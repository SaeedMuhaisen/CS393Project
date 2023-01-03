package com.SaeedAndEmre.CS393Project.Controller;

import com.SaeedAndEmre.CS393Project.DTO.LocationDTO;
import com.SaeedAndEmre.CS393Project.DTO.MemberDTO;
import com.SaeedAndEmre.CS393Project.Entities.Location;
import com.SaeedAndEmre.CS393Project.Entities.Member;
import com.SaeedAndEmre.CS393Project.Mappers.LocationMapper;
import com.SaeedAndEmre.CS393Project.Mappers.MemberMapper;
import com.SaeedAndEmre.CS393Project.Services.LocationService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController

public class LocationController {
    @Autowired
    LocationService locationService;

    @Operation(summary = "Save a new Location",
            description = "Insert Location details below")
    @PostMapping(value = "/Locations/new")
    public ResponseEntity<LocationDTO> save(LocationDTO locationDTO){
        try{
            Location location= LocationMapper.INSTANCE.toLocation(locationDTO);
            LocationDTO result=LocationMapper.INSTANCE.toLocationDTO(locationService.save(location));
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @Operation(summary = "Find a location by its code")
    @GetMapping(value="/Locations/{code}")
    public ResponseEntity<LocationDTO> findById(@PathVariable(value = "code") int code){
        try{
            Location location= locationService.findById(code);
            LocationDTO result=LocationMapper.INSTANCE.toLocationDTO(location);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (EmptyResultDataAccessException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping(value = "/Locations")
    @Operation(summary = "List all Locations")
    public ResponseEntity<List<LocationDTO>> findAll(){
        try{
            return new ResponseEntity<>(LocationMapper.INSTANCE.toLocationDTOS(locationService.findAll()), HttpStatus.OK);

        }catch (EmptyResultDataAccessException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
