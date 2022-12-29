package com.SaeedAndEmre.CS393Project.Repositories;

import com.SaeedAndEmre.CS393Project.Entities.Services;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicesRepository extends JpaRepository<Services,Long> {
}
