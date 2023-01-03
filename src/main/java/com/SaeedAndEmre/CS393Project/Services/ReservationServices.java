package com.SaeedAndEmre.CS393Project.Services;

import com.SaeedAndEmre.CS393Project.DTO.*;
import com.SaeedAndEmre.CS393Project.Entities.*;
import com.SaeedAndEmre.CS393Project.Mappers.CarMapper;
import com.SaeedAndEmre.CS393Project.Mappers.ReservationMapper;
import com.SaeedAndEmre.CS393Project.Repositories.*;
import net.bytebuddy.pool.TypePool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import javax.xml.crypto.Data;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ReservationServices {
    @Autowired
    ReservationRepository reservationRepository;
    @Autowired
    EquipmentRepository equipmentRepository; //should i do it with services?
    @Autowired
    ServicesRepository servicesRepository;
    @Autowired
    CarRepository carRepository;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    LocationRepository locationRepository;

    @Transactional(rollbackFor = {EmptyResultDataAccessException.class,RuntimeException.class})
    public ReservationInfoDTO save(ReservationDTO reservationDTO) throws Exception {
        Optional<Car> car = carRepository.findById(reservationDTO.getBarcode());
        List<Equipment> equipments = equipmentRepository.findAllById(reservationDTO.getEquipments());
        List<Services> services = servicesRepository.findAllById(reservationDTO.getServices());
        Optional<Member> member = memberRepository.findById(reservationDTO.getMemberId());
        Optional<Location> pickUpLocation = locationRepository.findById(reservationDTO.getPickUpLocation());
        Optional<Location> dropOffLocation = locationRepository.findById(reservationDTO.getDropOffLocation());

        if (car.isEmpty()
                || (reservationDTO.getEquipments().size() == 1 && reservationDTO.getEquipments().stream().toList().get(0) != 0 && equipments.size() != reservationDTO.getEquipments().size())
                || (reservationDTO.getServices().size() == 1 && reservationDTO.getServices().stream().toList().get(0) != 0 && services.size() != reservationDTO.getServices().size())
                || member.isEmpty() || pickUpLocation.isEmpty() || dropOffLocation.isEmpty()) {
            throw new Exception();
        } else {
            if (car.get().getStatus() != Car.CarStatus.AVAILABLE) {
                throw new Exception();
            } else {
                car.get().setStatus(Car.CarStatus.LOANED);
                Reservation reservation = new Reservation();
                reservation.setServices(services);
                reservation.setEquipments(equipments);
                reservation.setCar(car.get());
                reservation.setCreationDate(java.time.LocalDateTime.now());
                reservation.setPickUpDate(java.time.LocalDateTime.now().plusDays(1));
                reservation.setDropOffDate(java.time.LocalDateTime.now().plusDays(reservationDTO.getDayCount()));
                reservation.setDropOffLocation(dropOffLocation.get());
                reservation.setPickUpLocation(pickUpLocation.get());
                reservation.setMember(member.get());
                reservation.setStatus(Reservation.ReservationStatus.ACTIVE);
                reservation.setReturnDate(java.time.LocalDateTime.now().plusDays(reservationDTO.getDayCount()));
                double servicesCosts = 0;
                for (Services service : services) {
                    servicesCosts += service.getPrice();
                }
                double equipmentCosts = 0;
                for (Equipment equipment : equipments) {
                    equipmentCosts += equipment.getPrice();
                }
                double carRentCost = reservationDTO.getDayCount() * car.get().getDailyPrice();
                double totalCost = servicesCosts + equipmentCosts + carRentCost;

                return ReservationMapper.INSTANCE.toReservationInfoDTO(reservationRepository.save(reservation), totalCost);
            }
        }
    }

    //todo:should change price?
    @Transactional(rollbackFor = {Exception.class, EmptyResultDataAccessException.class})
    public Boolean addService(Long serviceId, Long reservationId) {
        Optional<Reservation> reservation = reservationRepository.findById(reservationId);
        Optional<Services> service = servicesRepository.findById(serviceId);
        if (reservation.isEmpty()) {
            throw new EmptyResultDataAccessException(1);
        } else if (service.isEmpty() || reservation.get().getServices().contains(service.get())) {
            return false;
        } else {
            try {
                reservation.get().getServices().add(service.get());
                reservationRepository.save(reservation.get());
                return true;
            } catch (Exception x) {
                return false;
            }

        }
    }

    @Transactional(rollbackFor = {Exception.class, EmptyResultDataAccessException.class})
    public Boolean addEquipment(Long equiptmentId, Long reservationId) {
        Optional<Reservation> reservation = reservationRepository.findById(reservationId);
        Optional<Equipment> equipment = equipmentRepository.findById(equiptmentId);
        if (reservation.isEmpty()) {
            throw new EmptyResultDataAccessException(1);
        } else if (equipment.isEmpty() || reservation.get().getEquipments().contains(equipment.get())) {
            return false;
        } else {
            try {
                reservation.get().getEquipments().add(equipment.get());
                reservationRepository.save(reservation.get());
                return true;
            } catch (Exception e) {
                return false;
            }
        }
    }

    @Transactional(rollbackFor = {Exception.class, EmptyResultDataAccessException.class})
    public Boolean cancelReservation(Long reservationId) {
        Optional<Reservation> reservation = reservationRepository.findById(reservationId);
        if (!reservation.isPresent()) {
            return false;
        } else {
            try {
                reservation.get().setStatus(Reservation.ReservationStatus.CANCELLED);
                reservation.get().getCar().setStatus(Car.CarStatus.AVAILABLE);
                return true;
            } catch (Exception e) {
                throw e;
            }

        }

    }

    public Reservation findByIdAndActive(Long reservationId) {
        Optional<Reservation> reservation = reservationRepository.findByCarIDAndActive(reservationId);
        if (!reservation.isEmpty()) {
            return reservation.get();
        } else {
            throw new RuntimeException();
        }
    }

    @Transactional(rollbackFor = {Exception.class, EmptyResultDataAccessException.class})
    public void finishReservation(Long reservationId) throws Exception {
        Optional<Reservation> reservation = reservationRepository.findById(reservationId);
        if (reservation.isEmpty()) {
            throw new EmptyResultDataAccessException(1);
        }
        Optional<Car> car = carRepository.findById(reservation.get().getCar().getBarcode());
        if (car.isEmpty()) {
            throw new EmptyResultDataAccessException(1);
        }
        try {
            reservation.get().setStatus(Reservation.ReservationStatus.COMPLETED);
            reservation.get().setReturnDate(java.time.LocalDateTime.now());
            car.get().setStatus(Car.CarStatus.AVAILABLE);
        } catch (Exception e) {
            throw new Exception();
        }
    }



}


