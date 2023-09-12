package com.project.mscars.domain.car.service;

import com.project.mscars.domain.car.dtos.CarRequestDTO;
import com.project.mscars.domain.car.dtos.CarResponseDTO;
import com.project.mscars.domain.car.exceptions.CarNotFoundException;
import com.project.mscars.domain.car.model.Car;
import com.project.mscars.domain.car.model.Pilot;
import com.project.mscars.domain.car.repository.CarRepository;
import com.project.mscars.domain.car.repository.PilotRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class CarServiceTest {
    @InjectMocks
    private CarService carService;
    @Spy
    private ModelMapper modelMapper;
    @Mock
    private CarRepository carRepository;
    @Mock
    private PilotRepository pilotRepository;
    @Test
    void mapCarToResponseDTO() {
    }

    @Test
    void createCar() {
        CarRequestDTO carRequestDTO = new CarRequestDTO();
        carRequestDTO.setBrand("Brand");
        carRequestDTO.setModel("Model");

        Pilot pilotRequestDTO = new Pilot();
        pilotRequestDTO.setName("Pilot Name");
        pilotRequestDTO.setAge(30);

        carRequestDTO.setPilot(pilotRequestDTO);

        Car car = new Car();
        car.setBrand(carRequestDTO.getBrand());
        car.setModel(carRequestDTO.getModel());

        Pilot pilot = new Pilot();
        pilot.setName(pilotRequestDTO.getName());
        pilot.setAge(pilotRequestDTO.getAge());

        when(pilotRepository.save(any(Pilot.class))).thenReturn(pilot);
        when(carRepository.save(any(Car.class))).thenReturn(car);

        CarResponseDTO carResponseDTO = carService.createCar(carRequestDTO);

        assertEquals("Brand", carResponseDTO.getBrand());
        assertEquals("Model", carResponseDTO.getModel());
    }

    @Test
    void getAllCars() {
        Car car1 = new Car();
        car1.setBrand("Brand1");
        car1.setModel("Model1");

        Car car2 = new Car();
        car2.setBrand("Brand2");
        car2.setModel("Model2");

        List<Car> carList = new ArrayList<>();
        carList.add(car1);
        carList.add(car2);

        when(carRepository.findAll()).thenReturn(carList);

        List<CarResponseDTO> expectedCarResponseDTOs = carList.stream()
                .map(car -> {
                    CarResponseDTO dto = new CarResponseDTO();
                    dto.setBrand(car.getBrand());
                    dto.setModel(car.getModel());
                    return dto;
                })
                .collect(Collectors.toList());

        List<CarResponseDTO> carResponseDTOs = carService.getAllCars();

        assertEquals(expectedCarResponseDTOs.size(), carResponseDTOs.size());
        assertEquals(expectedCarResponseDTOs, carResponseDTOs);
    }

    @Test
    void updateCar() throws CarNotFoundException {

        Long carId = 1L;
        Car existingCar = new Car();
        existingCar.setIdCar(carId);
        existingCar.setBrand("OldBrand");
        existingCar.setModel("OldModel");

        CarRequestDTO updatedCarRequest = new CarRequestDTO();
        updatedCarRequest.setBrand("NewBrand");
        updatedCarRequest.setModel("NewModel");

        when(carRepository.findById(carId)).thenReturn(Optional.of(existingCar));

        CarResponseDTO updatedCarResponse;
        updatedCarResponse = carService.updateCar(carId, updatedCarRequest);

        verify(carRepository).save(existingCar);

        assertEquals("NewBrand", updatedCarResponse.getBrand());
        assertEquals("NewModel", updatedCarResponse.getModel());
    }

    @Test
    void deleteCar()throws CarNotFoundException {
        Long carId = 1L;
        Car existingCar = new Car();
        existingCar.setIdCar(carId);

        when(carRepository.findById(carId)).thenReturn(Optional.of(existingCar));

        carService.deleteCar(carId);

        verify(carRepository).delete(existingCar);
    }
}