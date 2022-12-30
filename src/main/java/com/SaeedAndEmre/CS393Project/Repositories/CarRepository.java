package com.SaeedAndEmre.CS393Project.Repositories;

import com.SaeedAndEmre.CS393Project.Entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car,Long> {
    @Query(value = "select * from Car c where c.status='AVAILABLE' and c.type=?1 and c.transmission_type=?2", nativeQuery = true)
    List<Car> findAvailableByTypeAndTransmission(String type, String transmission);

    @Query(value = "select * from Car c where c.status='LOANED' or c.status='RESERVED'", nativeQuery = true)
    List<Car> findAllRented();
    Car findById(long barcode);
    Car findByBarcode(long barcode);
    Car save(Car car);
    @Query(nativeQuery = true, value = "UPDATE Car set license_plate_number=?1 and type=?2"+
            " and brand=?3 and daily_price=?4 and license_plate_number=?5 and mileage=?6 " +
            "and model=?7 and passenger_capacity=?8 and status=?9 where barcode=?10")
    Car update(Car car);
}