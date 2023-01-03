package com.SaeedAndEmre.CS393Project.Controller;

import com.SaeedAndEmre.CS393Project.DTO.EquipmentDTO;
import com.SaeedAndEmre.CS393Project.DTO.ServicesDTO;
import com.SaeedAndEmre.CS393Project.Entities.Equipment;
import com.SaeedAndEmre.CS393Project.Entities.Services;
import com.SaeedAndEmre.CS393Project.Mappers.EquipmentMapper;
import com.SaeedAndEmre.CS393Project.Mappers.ServicesMapper;
import com.SaeedAndEmre.CS393Project.Services.ServicesService;
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

public class ServicesController {
    @Autowired
    ServicesService servicesService;

    @PostMapping(value="/Services/new")
    public ResponseEntity<ServicesDTO> save(ServicesDTO servicesDTO){
        try{
            Services service = ServicesMapper.INSTANCE.toService(servicesDTO);
            ServicesDTO result=ServicesMapper.INSTANCE.toServiceDTO(servicesService.save(service));
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }
    @GetMapping(value = "/Services/{id}")
    public ResponseEntity<ServicesDTO> findById(@PathVariable(value = "id") Long id){
        try{
            Services services= servicesService.findById(id);
            ServicesDTO result= ServicesMapper.INSTANCE.toServiceDTO(services);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (EmptyResultDataAccessException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value="/Services")
    public ResponseEntity<List<ServicesDTO>> findAll(){
        try{
            return new ResponseEntity<>(ServicesMapper.INSTANCE.toServicesDTOS(servicesService.findAll()), HttpStatus.OK);

        }catch (EmptyResultDataAccessException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
