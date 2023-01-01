package com.SaeedAndEmre.CS393Project.Services;

import com.SaeedAndEmre.CS393Project.Entities.Equipment;
import com.SaeedAndEmre.CS393Project.Repositories.EquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public class EquipmentService {
    @Autowired
    EquipmentRepository equipmentRepository;

    public Equipment save(Equipment equipment){
        try {
            Equipment result = equipmentRepository.save(equipment);
            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Equipment findById(Long id){
        Optional<Equipment> equipment=equipmentRepository.findById(id);
        if(equipment.isPresent()){
            return equipment.get();
        }
        else{
            throw new EmptyResultDataAccessException(1);
        }
    }
    public List<Equipment> findAll(){
        List<Equipment> equipments= equipmentRepository.findAll();
        if(equipments.isEmpty()){
            throw new EmptyResultDataAccessException(1);
        }
        else{
            return equipments;
        }
    }
}
