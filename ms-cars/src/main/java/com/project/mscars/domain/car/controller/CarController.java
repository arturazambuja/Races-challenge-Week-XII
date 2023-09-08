package com.project.mscars.domain.car.controller;

import com.project.mscars.domain.car.dtos.CarRequestDTO;
import com.project.mscars.domain.car.dtos.CarResponseDTO;
import com.project.mscars.domain.car.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/cars")
public class CarController {
    private final CarService carService;
    @Autowired
    public CarController(CarService carService){
        this.carService = carService;
    }
    @GetMapping
    public List<CarResponseDTO> getAllCars(){
        return carService.getAllCars();
    }
    @PostMapping
    public ResponseEntity<String> createCar(@RequestBody CarRequestDTO requestDTO) {
        carService.createCar(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Car created successfully");
    }
}
