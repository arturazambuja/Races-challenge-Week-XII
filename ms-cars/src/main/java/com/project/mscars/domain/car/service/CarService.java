package com.project.mscars.domain.car.service;

import com.project.mscars.domain.car.dtos.CarRequestDTO;
import com.project.mscars.domain.car.dtos.CarResponseDTO;
import com.project.mscars.domain.car.dtos.PilotResponseDTO;
import com.project.mscars.domain.car.model.Car;
import com.project.mscars.domain.car.model.Pilot;
import com.project.mscars.domain.car.repository.CarRepository;
import com.project.mscars.domain.car.repository.PilotRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarService {
    private final ModelMapper modelMapper;
    private final CarRepository carRepository;
    private final PilotRepository pilotRepository;

    @Autowired
    public CarService(ModelMapper modelMapper, CarRepository carRepository, PilotRepository pilotRepository) {
        this.modelMapper = modelMapper;
        this.carRepository = carRepository;
        this.pilotRepository = pilotRepository;
    }
    public CarResponseDTO mapCarToResponseDTO(Car car) {
        return modelMapper.map(car, CarResponseDTO.class);
    }
    public CarResponseDTO createCar(CarRequestDTO carRequestDTO) {
        Car car = modelMapper.map(carRequestDTO, Car.class);

        if (carRequestDTO.getPilot() != null) {
            Pilot pilot = new Pilot();
            pilot.setName(carRequestDTO.getPilot().getName());
            pilot.setAge(carRequestDTO.getPilot().getAge());

            car.setPilot(pilot);

            pilotRepository.save(pilot);
        }

        car = carRepository.save(car);

        return modelMapper.map(car, CarResponseDTO.class);
    }
    public List<CarResponseDTO> getAllCars() {
        List<Car> classrooms = carRepository.findAll();
        return classrooms.stream()
                .map(this::mapCarToResponseDTO)
                .collect(Collectors.toList());
    }
}
