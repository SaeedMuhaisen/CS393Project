package com.SaeedAndEmre.CS393Project.Repositories;

import com.SaeedAndEmre.CS393Project.Entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation,Long> {

}
