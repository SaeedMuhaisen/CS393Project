package com.SaeedAndEmre.CS393Project.Services;

import com.SaeedAndEmre.CS393Project.Entities.Equipment;
import com.SaeedAndEmre.CS393Project.Entities.Services;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class EquipmentServiceTest {
    @Autowired
    EquipmentService equipmentServices;

    @Test
    void save() {
        Equipment equipment=new Equipment();
        equipment.setPrice(55);
        equipment.setName("123");
        equipment.setEquipmentId(equipmentServices.save(equipment).getEquipmentId());

        Equipment result= equipmentServices.findById(equipment.getEquipmentId());

        assertEquals(equipment.getEquipmentId(),result.getEquipmentId());
        assertEquals(equipment.getPrice(),result.getPrice());
        assertEquals(equipment.getName(),result.getName());

    }

    @Test
    void findById() {
        Equipment equipment=new Equipment();
        equipment.setPrice(100);
        equipment.setName("abc");
        equipment.setEquipmentId(equipmentServices.save(equipment).getEquipmentId());

        Long id=equipment.getEquipmentId();

        Equipment result= equipmentServices.findById(id);
        assertEquals(equipment.getEquipmentId(),result.getEquipmentId());
        assertEquals(equipment.getPrice(),result.getPrice());
        assertEquals(equipment.getName(),result.getName());



    }

    @Test
    void findAll() {
        Equipment equipment1=new Equipment();
        equipment1.setPrice(100);
        equipment1.setName("Temp");
        equipment1.setEquipmentId(equipmentServices.save(equipment1).getEquipmentId());

        Equipment equipment2=new Equipment();
        equipment2.setPrice(150);
        equipment2.setName("Tempa");
        equipment2.setEquipmentId(equipmentServices.save(equipment2).getEquipmentId());

        List<Equipment> result= equipmentServices.findAll();
        assertEquals(2,result.size());
        assertEquals(equipment1.getEquipmentId(),result.get(0).getEquipmentId());
        assertEquals(equipment1.getName(),result.get(0).getName());
        assertEquals(equipment1.getPrice(),result.get(0).getPrice());

        assertEquals(equipment2.getEquipmentId(),result.get(1).getEquipmentId());
        assertEquals(equipment2.getName(),result.get(1).getName());
        assertEquals(equipment2.getPrice(),result.get(1).getPrice());
    }
}