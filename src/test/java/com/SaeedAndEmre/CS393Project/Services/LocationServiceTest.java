package com.SaeedAndEmre.CS393Project.Services;

import com.SaeedAndEmre.CS393Project.Entities.Equipment;
import com.SaeedAndEmre.CS393Project.Entities.Location;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@AutoConfigureTestDatabase
class LocationServiceTest {

    @Autowired
    LocationService locationService;
    @BeforeEach

    public void reset(){
        locationService.deleteAll();
    }
    @Test
    void save() {
        Location location=new Location();
        location.setName("123");
        location.setAddress("abc");
        location.setCode(locationService.save(location).getCode());

        Location result= locationService.findById(location.getCode());

        assertEquals(location.getAddress(),result.getAddress());
        assertEquals(location.getName(),result.getName());
        assertEquals(location.getCode(),result.getCode());

    }

    @Test
    void findById() {
        Location location=new Location();
        location.setName("123");
        location.setAddress("abc");
        location.setCode(locationService.save(location).getCode());

        int id=location.getCode();

        Location result= locationService.findById(id);
        assertEquals(location.getAddress(),result.getAddress());
        assertEquals(location.getName(),result.getName());
        assertEquals(location.getCode(),result.getCode());



    }

    @Test
    void findAll() {
        Location location1=new Location();
        location1.setName("111");
        location1.setAddress("aaa");
        location1.setCode(locationService.save(location1).getCode());

        Location location2=new Location();
        location2.setName("222");
        location2.setAddress("bbb");
        location2.setCode(locationService.save(location2).getCode());

        List<Location> result= locationService.findAll();
        assertEquals(2,result.size());
        assertEquals(location1.getName(),result.get(0).getName());
        assertEquals(location1.getAddress(),result.get(0).getAddress());
        assertEquals(location1.getCode(),result.get(0).getCode());

        assertEquals(location2.getName(),result.get(1).getName());
        assertEquals(location2.getAddress(),result.get(1).getAddress());
        assertEquals(location2.getCode(),result.get(1).getCode());
    }
}