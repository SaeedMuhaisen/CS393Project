package com.SaeedAndEmre.CS393Project;

import com.SaeedAndEmre.CS393Project.DTO.CreateCarDTO;
import com.SaeedAndEmre.CS393Project.DTO.TypeAndTransmissionDTO;
import com.SaeedAndEmre.CS393Project.Entities.Car;
import com.SaeedAndEmre.CS393Project.Mappers.CarMapper;
import com.SaeedAndEmre.CS393Project.Services.CarService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CarServiceTests {

    @Autowired
    CarService carService;

    @BeforeEach
    public void reset(){
        carService.deleteAll();
    }

    @Test
    @Order(1)
    public void save(){
        Car test=new Car();
        test.setStatus(Car.CarStatus.AVAILABLE);
        test.setType(Car.CarType.STANDARD);
        //test.setBarcode(1111L);
        test.setPassengerCapacity(4);
        test.setModel("2004");
        test.setTransmissionType("AUTOMATIC");
        test.setLicensePlateNumber("abc");
        test.setDailyPrice(15.5f);
        test.setMileage(0);
        test.setBrand("BMW");
        CreateCarDTO createCarDTO=carService.save(CarMapper.INSTANCE.fromCarToCreateCarDTO(test));
        test.setBarcode(createCarDTO.getBarcode());
        Car result=CarMapper.INSTANCE.fromCreateCarDTOtoCar(createCarDTO);
        assertEquals(test.getStatus(),result.getStatus());
        assertEquals(test.getType(),result.getType());
        assertEquals(test.getBarcode(),result.getBarcode());
        assertEquals(test.getPassengerCapacity(),result.getPassengerCapacity());
        assertEquals(test.getModel(),result.getModel());
        assertEquals(test.getTransmissionType(),result.getTransmissionType());
        assertEquals(test.getLicensePlateNumber(),result.getLicensePlateNumber());
        assertEquals(test.getDailyPrice(),result.getDailyPrice());
        assertEquals(test.getMileage(),result.getMileage());
        assertEquals(test.getBrand(),result.getBrand());
    }
    @Test()
    @Order(2)
    public void update(){
        Car test=new Car();
        test.setStatus(Car.CarStatus.AVAILABLE);
        test.setType(Car.CarType.STANDARD);
        test.setPassengerCapacity(4);
        test.setModel("2004");
        test.setTransmissionType("AUTOMATIC");
        test.setLicensePlateNumber("abc");
        test.setDailyPrice(15.5f);
        test.setMileage(0);
        test.setBrand("BMW");
        CreateCarDTO createCarDTO=carService.save(CarMapper.INSTANCE.fromCarToCreateCarDTO(test));
        test.setBarcode(createCarDTO.getBarcode());
        Car result=CarMapper.INSTANCE.fromCreateCarDTOtoCar(createCarDTO);
        assertEquals(test.getStatus(),result.getStatus());
        assertEquals(test.getType(),result.getType());

        test.setStatus(Car.CarStatus.LOANED);
        test.setMileage(100);
        test.setBrand("ABC");
        createCarDTO=carService.save(CarMapper.INSTANCE.fromCarToCreateCarDTO(test));
        result=CarMapper.INSTANCE.fromCreateCarDTOtoCar(createCarDTO);

        assertEquals(test.getStatus(),result.getStatus());
        assertEquals(test.getMileage(),result.getMileage());
        assertEquals(test.getBrand(),result.getBrand());
        assertEquals(test.getBarcode(),result.getBarcode());

        test.setBarcode(2L);
        test.setBrand("bbbb");
        carService.save(CarMapper.INSTANCE.fromCarToCreateCarDTO(test));
        int i=0;

    }
    @Test
    public void find(){
        Car test=new Car();
        test.setStatus(Car.CarStatus.AVAILABLE);
        test.setType(Car.CarType.STANDARD);
        //test.setBarcode(1111L);
        test.setPassengerCapacity(4);
        test.setModel("2004");
        test.setTransmissionType("AUTOMATIC");
        test.setLicensePlateNumber("abc");
        test.setDailyPrice(15.5f);
        test.setMileage(0);
        test.setBrand("BMW");
        CreateCarDTO createCarDTO=carService.save(CarMapper.INSTANCE.fromCarToCreateCarDTO(test));
        test.setBarcode(createCarDTO.getBarcode());

        Car result=CarMapper.INSTANCE.fromCreateCarDTOtoCar(carService.findByBarcode(test.getBarcode()));

        assertEquals(test.getStatus(),result.getStatus());
        assertEquals(test.getType(),result.getType());
        assertEquals(test.getBarcode(),result.getBarcode());
        assertEquals(test.getPassengerCapacity(),result.getPassengerCapacity());
        assertEquals(test.getModel(),result.getModel());
        assertEquals(test.getTransmissionType(),result.getTransmissionType());
        assertEquals(test.getLicensePlateNumber(),result.getLicensePlateNumber());
        assertEquals(test.getDailyPrice(),result.getDailyPrice());
        assertEquals(test.getMileage(),result.getMileage());
        assertEquals(test.getBrand(),result.getBrand());
    }

    @Test
    public void findAll(){
        Car test1=new Car();
        test1.setStatus(Car.CarStatus.AVAILABLE);
        test1.setType(Car.CarType.STANDARD);
        test1.setPassengerCapacity(4);
        test1.setModel("2004");
        test1.setTransmissionType("AUTOMATIC");
        test1.setLicensePlateNumber("abc");
        test1.setDailyPrice(15.5f);
        test1.setMileage(0);
        test1.setBrand("BMW");
        CreateCarDTO createCarDTO1=carService.save(CarMapper.INSTANCE.fromCarToCreateCarDTO(test1));
        test1.setBarcode(createCarDTO1.getBarcode());

        Car result1=CarMapper.INSTANCE.fromCreateCarDTOtoCar(carService.findByBarcode(test1.getBarcode()));

        Car test2=new Car();
        test2.setStatus(Car.CarStatus.AVAILABLE);
        test2.setType(Car.CarType.STANDARD);
        test2.setPassengerCapacity(4);
        test2.setModel("2004");
        test2.setTransmissionType("AUTOMATIC");
        test2.setLicensePlateNumber("abc");
        test2.setDailyPrice(15.5f);
        test2.setMileage(0);
        test2.setBrand("BMW");
        CreateCarDTO createCarDTO2=carService.save(CarMapper.INSTANCE.fromCarToCreateCarDTO(test2));
        test2.setBarcode(createCarDTO2.getBarcode());

        Car result2=CarMapper.INSTANCE.fromCreateCarDTOtoCar(carService.findByBarcode(test2.getBarcode()));

        Car test3=new Car();
        test3.setStatus(Car.CarStatus.AVAILABLE);
        test3.setType(Car.CarType.STANDARD);
        test3.setPassengerCapacity(4);
        test3.setModel("2004");
        test3.setTransmissionType("AUTOMATIC");
        test3.setLicensePlateNumber("abc");
        test3.setDailyPrice(15.5f);
        test3.setMileage(0);
        test3.setBrand("BMW");
        CreateCarDTO createCarDTO3=carService.save(CarMapper.INSTANCE.fromCarToCreateCarDTO(test3));
        test3.setBarcode(createCarDTO3.getBarcode());

        Car result3=CarMapper.INSTANCE.fromCreateCarDTOtoCar(carService.findByBarcode(test3.getBarcode()));

        List<Car> cars=CarMapper.INSTANCE.fromCreateCarDTOSToCars(carService.findAll());

        assertEquals(cars.size(),3);

        assertEquals(test1.getStatus(),cars.get(0).getStatus());
        assertEquals(test1.getType(),cars.get(0).getType());
        assertEquals(test1.getBarcode(),cars.get(0).getBarcode());
        assertEquals(test1.getPassengerCapacity(),cars.get(0).getPassengerCapacity());
        assertEquals(test1.getModel(),cars.get(0).getModel());
        assertEquals(test1.getTransmissionType(),cars.get(0).getTransmissionType());
        assertEquals(test1.getLicensePlateNumber(),cars.get(0).getLicensePlateNumber());
        assertEquals(test1.getDailyPrice(),cars.get(0).getDailyPrice());
        assertEquals(test1.getMileage(),cars.get(0).getMileage());
        assertEquals(test1.getBrand(),cars.get(0).getBrand());

        assertEquals(test2.getStatus(),cars.get(1).getStatus());
        assertEquals(test2.getType(),cars.get(1).getType());
        assertEquals(test2.getBarcode(),cars.get(1).getBarcode());
        assertEquals(test2.getPassengerCapacity(),cars.get(1).getPassengerCapacity());
        assertEquals(test2.getModel(),cars.get(1).getModel());
        assertEquals(test2.getTransmissionType(),cars.get(1).getTransmissionType());
        assertEquals(test2.getLicensePlateNumber(),cars.get(1).getLicensePlateNumber());
        assertEquals(test2.getDailyPrice(),cars.get(1).getDailyPrice());
        assertEquals(test2.getMileage(),cars.get(1).getMileage());
        assertEquals(test2.getBrand(),cars.get(1).getBrand());

        assertEquals(test3.getStatus(),cars.get(2).getStatus());
        assertEquals(test3.getType(),cars.get(2).getType());
        assertEquals(test3.getBarcode(),cars.get(2).getBarcode());
        assertEquals(test3.getPassengerCapacity(),cars.get(2).getPassengerCapacity());
        assertEquals(test3.getModel(),cars.get(2).getModel());
        assertEquals(test3.getTransmissionType(),cars.get(2).getTransmissionType());
        assertEquals(test3.getLicensePlateNumber(),cars.get(2).getLicensePlateNumber());
        assertEquals(test3.getDailyPrice(),cars.get(2).getDailyPrice());
        assertEquals(test3.getMileage(),cars.get(2).getMileage());
        assertEquals(test3.getBrand(),cars.get(2).getBrand());
    }

    @Test
    public void findByTypeAndTransmission(){
        Car test1=new Car();
        test1.setStatus(Car.CarStatus.AVAILABLE);
        test1.setType(Car.CarType.SUV);
        test1.setPassengerCapacity(4);
        test1.setModel("2004");
        test1.setTransmissionType("AUTOMATIC");
        test1.setLicensePlateNumber("abc");
        test1.setDailyPrice(15.5f);
        test1.setMileage(0);
        test1.setBrand("BMW");
        CreateCarDTO createCarDTO1=carService.save(CarMapper.INSTANCE.fromCarToCreateCarDTO(test1));
        test1.setBarcode(createCarDTO1.getBarcode());

        Car result1=CarMapper.INSTANCE.fromCreateCarDTOtoCar(carService.findByBarcode(test1.getBarcode()));

        Car test2=new Car();
        test2.setStatus(Car.CarStatus.AVAILABLE);
        test2.setType(Car.CarType.STANDARD);
        test2.setPassengerCapacity(4);
        test2.setModel("2004");
        test2.setTransmissionType("AUTOMATIC");
        test2.setLicensePlateNumber("abc");
        test2.setDailyPrice(15.5f);
        test2.setMileage(0);
        test2.setBrand("BMW");
        CreateCarDTO createCarDTO2=carService.save(CarMapper.INSTANCE.fromCarToCreateCarDTO(test2));
        test2.setBarcode(createCarDTO2.getBarcode());

        Car result2=CarMapper.INSTANCE.fromCreateCarDTOtoCar(carService.findByBarcode(test2.getBarcode()));

        TypeAndTransmissionDTO a=new TypeAndTransmissionDTO();
        a.setType("SUV");
        a.setTransmissionType("AUTOMATIC");

        List<Car> cars=CarMapper.INSTANCE.toCars(carService.findAvailableByTypeAndTransmission(a));

        assertEquals(1,cars.size());
        assertEquals(cars.get(0).getBarcode(),test1.getBarcode());
        assertEquals(cars.get(0).getType(),test1.getType());
        assertEquals(cars.get(0).getTransmissionType(),test1.getTransmissionType());


        a.setType("STANDARD");
        a.setTransmissionType("AUTOMATIC");
        cars=CarMapper.INSTANCE.toCars(carService.findAvailableByTypeAndTransmission(a));

        assertEquals(1,cars.size());
        assertEquals(cars.get(0).getBarcode(),test2.getBarcode());
        assertEquals(cars.get(0).getType(),test2.getType());
        assertEquals(cars.get(0).getTransmissionType(),test2.getTransmissionType());

    }
    @Test
    public void delete(){
        Car test=new Car();
        test.setStatus(Car.CarStatus.AVAILABLE);
        test.setType(Car.CarType.STANDARD);
        test.setPassengerCapacity(4);
        test.setModel("2004");
        test.setTransmissionType("AUTOMATIC");
        test.setLicensePlateNumber("abc");
        test.setDailyPrice(15.5f);
        test.setMileage(0);
        test.setBrand("BMW");
        CreateCarDTO createCarDTO=carService.save(CarMapper.INSTANCE.fromCarToCreateCarDTO(test));
        test.setBarcode(createCarDTO.getBarcode());
        Car result=CarMapper.INSTANCE.fromCreateCarDTOtoCar(createCarDTO);
        assertEquals(test.getStatus(),result.getStatus());
        assertEquals(test.getType(),result.getType());

        carService.deleteById(result.getBarcode());

        Optional<CreateCarDTO> result1= Optional.ofNullable(carService.findByBarcode(test.getBarcode()));
        assert(result1.isEmpty());
    }
}
