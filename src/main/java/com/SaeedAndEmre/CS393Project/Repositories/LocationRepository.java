package com.SaeedAndEmre.CS393Project.Repositories;

import com.SaeedAndEmre.CS393Project.Entities.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location,Integer> {
    Location findByCode(int code);
}
