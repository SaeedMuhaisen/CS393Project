package com.SaeedAndEmre.CS393Project.Services;

import com.SaeedAndEmre.CS393Project.DTO.ReservationDTO;
import com.SaeedAndEmre.CS393Project.DTO.ReservationInfoDTO;
import com.SaeedAndEmre.CS393Project.Entities.*;
import com.SaeedAndEmre.CS393Project.Mappers.ReservationMapper;
import com.SaeedAndEmre.CS393Project.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationServices {
    @Autowired
    ReservationRepository reservationRepository;
    @Autowired
    EquipmentRepository equipmentRepository; //should i do it with services?
    @Autowired
    ServicesRepository servicesRepository;
    @Autowired
    CarService carService;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    LocationRepository locationRepository;

    public ReservationInfoDTO save(ReservationDTO reservationDTO){
        List<Equipment> equipments=equipmentRepository.findByEquipmentIds(reservationDTO.getEquipments());
        List<Services> services=servicesRepository.findAllById(reservationDTO.getServices());
        Car car=carService.findByBarcode(reservationDTO.getBarcode());
        Member member=memberRepository.findByMemberId(reservationDTO.getMemberId());
        Location pickUpLocation=locationRepository.findByCode(reservationDTO.getPickUpLocation());
        Location dropOffLocation=locationRepository.findByCode(reservationDTO.getDropOffLocation());
        Reservation reservation=new Reservation();
        reservation.setServices(services);
        reservation.setEquipments(equipments);
        reservation.setCar(car);
        reservation.setCreationDate(java.time.LocalDateTime.now());
        reservation.setPickUpDate(java.time.LocalDateTime.now().plusDays(1));
        reservation.setDropOffDate(java.time.LocalDateTime.now().plusDays(reservationDTO.getDayCount()));
        reservation.setDropOffLocation(dropOffLocation);
        reservation.setPickUpLocation(pickUpLocation);
        reservation.setMember(member);
        reservation.setStatus(Reservation.ReservationStatus.ACTIVE);
        reservation.setReturnDate(java.time.LocalDateTime.now().plusDays(reservationDTO.getDayCount()));
        double servicesCosts=0;
        for(Services service:services){
            servicesCosts+=service.getPrice();
        }
        double equipmentCosts=0;
        for(Equipment equipment: equipments){
            equipmentCosts+=equipment.getPrice();
        }
        double carRentCost=reservationDTO.getDayCount()*car.getDailyPrice();
        double totalCost=servicesCosts+equipmentCosts+carRentCost;



        ReservationInfoDTO reservationInfoDTO=ReservationMapper.INSTANCE.toReservationInfoDTO(reservationRepository.save(reservation),totalCost);
        int i=0;
        return reservationInfoDTO;
    }

}
