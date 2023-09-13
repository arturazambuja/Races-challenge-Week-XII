package com.project.msraces.config;

import com.project.mscars.domain.car.dtos.CarResponseDTO;
import com.project.mscars.domain.car.model.Car;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "ms-cars", url = "${http://localhost:8080/v1/cars}")
public interface CarFeignClient {
    @GetMapping("/v1/cars/{idCar}")
    List<Car> getCarsByIds(@RequestParam List<Long> carIds);

    CarResponseDTO getCarById(Long carId);
}
