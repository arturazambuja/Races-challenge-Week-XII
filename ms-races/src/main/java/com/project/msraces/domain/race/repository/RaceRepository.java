package com.project.msraces.domain.race.repository;

import com.project.msraces.domain.race.model.Race;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RaceRepository extends JpaRepository<Race, Long> {
}
