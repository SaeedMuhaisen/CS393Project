package com.SaeedAndEmre.CS393Project.Controller;

import com.SaeedAndEmre.CS393Project.DTO.EquipmentDTO;
import com.SaeedAndEmre.CS393Project.Entities.Equipment;
import com.SaeedAndEmre.CS393Project.Mappers.EquipmentMapper;
import com.SaeedAndEmre.CS393Project.Services.EquipmentService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class EquipmentController {

    @Autowired
    EquipmentService equipmentService;

    @PostMapping(value="/Equipments/new")
    @Operation(summary = "Save a new equipment",
            description = "Insert equipment details below")
    public ResponseEntity<EquipmentDTO> save(EquipmentDTO equipmentDTO){
        try{
            Equipment equipment= EquipmentMapper.INSTANCE.toEquipment(equipmentDTO);
            EquipmentDTO result=EquipmentMapper.INSTANCE.toEquipmentDTO(equipmentService.save(equipment));
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }
    @GetMapping(value = "/Equipments/{id}")
    @Operation(summary = "Search equipments with id",
            description = "Insert equipment id below")
    public ResponseEntity<EquipmentDTO> findById(@PathVariable(value = "id") Long id){
        try{
            Equipment equipment= equipmentService.findById(id);
            EquipmentDTO result= EquipmentMapper.INSTANCE.toEquipmentDTO(equipment);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (EmptyResultDataAccessException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value="/Equipments")
    @Operation(summary = "List All Equipments")
    public ResponseEntity<List<EquipmentDTO>> findAll(){
        try{
            return new ResponseEntity<>(EquipmentMapper.INSTANCE.toEquipmentDTOS(equipmentService.findAll()), HttpStatus.OK);

        }catch (EmptyResultDataAccessException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
