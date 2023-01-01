package com.SaeedAndEmre.CS393Project.Services;

import com.SaeedAndEmre.CS393Project.Entities.Equipment;
import com.SaeedAndEmre.CS393Project.Entities.Location;
import com.SaeedAndEmre.CS393Project.Repositories.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.List;
import java.util.Optional;

public class LocationService {
    @Autowired
    LocationRepository locationRepository;

    public Location save(Location location){
        try {
            Location result = locationRepository.save(location);
            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public Location findById(int id){
        Optional<Location> location=locationRepository.findById(id);
        if(location.isPresent()){
            return location.get();
        }
        else{
            throw new EmptyResultDataAccessException(1);
        }
    }

    public List<Location> findAll(){
        List<Location> locations= locationRepository.findAll();
        if(locations.isEmpty()){
            throw new EmptyResultDataAccessException(1);
        }
        else{
            return locations;
        }
    }
}
