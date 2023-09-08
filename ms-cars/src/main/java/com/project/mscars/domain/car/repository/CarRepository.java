package com.project.mscars.domain.car.repository;

import com.project.mscars.domain.car.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {
}
