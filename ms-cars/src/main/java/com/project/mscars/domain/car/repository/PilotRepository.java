package com.project.mscars.domain.car.repository;

import com.project.mscars.domain.car.model.Pilot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PilotRepository extends JpaRepository<Pilot, Long> {
}
