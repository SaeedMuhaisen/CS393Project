package com.SaeedAndEmre.CS393Project.Services;

import com.SaeedAndEmre.CS393Project.Entities.Equipment;
import com.SaeedAndEmre.CS393Project.Entities.Services;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ServicesServiceTest {
    @Autowired
    ServicesService servicesService;

    @Test
    void save() {
        Services services=new Services();
        services.setPrice(100);
        services.setName("Temp");
        services.setServiceId(servicesService.save(services).getServiceId());

        Services result=servicesService.findById(services.getServiceId());

        assertEquals(services.getServiceId(),result.getServiceId());
        assertEquals(services.getPrice(),result.getPrice());
        assertEquals(services.getName(),result.getName());

    }

    @Test
    void findById() {
        Services services=new Services();
        services.setPrice(100);
        services.setName("Temp");
        services.setServiceId(servicesService.save(services).getServiceId());

        Long id=services.getServiceId();

        Services result=servicesService.findById(id);
        assertEquals(services.getServiceId(),result.getServiceId());
        assertEquals(services.getPrice(),result.getPrice());
        assertEquals(services.getName(),result.getName());



    }

    @Test
    void findAll() {
        Services services1=new Services();
        services1.setPrice(100);
        services1.setName("Temp");
        services1.setServiceId(servicesService.save(services1).getServiceId());

        Services services2=new Services();
        services2.setPrice(100);
        services2.setName("Temp");
        services2.setServiceId(servicesService.save(services2).getServiceId());

        List<Services> result=servicesService.findAll();
        assertEquals(2,result.size());
        assertEquals(services1.getServiceId(),result.get(0).getServiceId());
        assertEquals(services1.getName(),result.get(0).getName());
        assertEquals(services1.getPrice(),result.get(0).getPrice());

        assertEquals(services2.getServiceId(),result.get(1).getServiceId());
        assertEquals(services2.getName(),result.get(1).getName());
        assertEquals(services2.getPrice(),result.get(1).getPrice());
    }
}