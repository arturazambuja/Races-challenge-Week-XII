package com.project.mscars.domain.car.controller;


import com.project.mscars.domain.car.dtos.CarRequestDTO;
import com.project.mscars.domain.car.exceptions.CarNotFoundException;
import org.junit.internal.runners.JUnit4ClassRunner;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import com.project.mscars.domain.car.dtos.CarResponseDTO;
import com.project.mscars.domain.car.service.CarService;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@WebMvcTest(CarController.class)
public class CarControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @InjectMocks
    private CarController carController;
    @Spy
    private ModelMapper modelMapper;

    @MockBean
    private CarService carService;
    @Test
    void getAllCars(){

        List<CarResponseDTO> expectedCarResponseDTOs = new ArrayList<>();
        expectedCarResponseDTOs.add(new CarResponseDTO());
        expectedCarResponseDTOs.add(new CarResponseDTO());

        when(carService.getAllCars()).thenReturn(expectedCarResponseDTOs);

        List<CarResponseDTO> actualCarResponseDTOs;
        actualCarResponseDTOs = carController.getAllCars();

        assertEquals(expectedCarResponseDTOs, actualCarResponseDTOs);
    }

    @Test
    void createCar() {CarRequestDTO requestDTO = new CarRequestDTO();
        requestDTO.setBrand("Brand");
        requestDTO.setModel("Model");

        ResponseEntity<String> response = carController.createCar(requestDTO);

        verify(carService, times(1)).createCar(requestDTO);

        assertAll(
                () -> assertEquals(HttpStatus.CREATED, response.getStatusCode()),
                () -> assertEquals("Car created successfully", response.getBody())
        );
    }

    @Test
    void updateCar() throws CarNotFoundException {

            Long carId = 1L;
            CarRequestDTO requestDTO = new CarRequestDTO();
            requestDTO.setBrand("UpdatedBrand");
            requestDTO.setModel("UpdatedModel");
            CarResponseDTO expectedResponseDTO = new CarResponseDTO();
            expectedResponseDTO.setIdCar(carId);
            expectedResponseDTO.setBrand(requestDTO.getBrand());
            expectedResponseDTO.setModel(requestDTO.getModel());

            when(carService.updateCar(carId, requestDTO)).thenReturn(expectedResponseDTO);

            CarResponseDTO responseDTO = carController.updateCar(carId, requestDTO);

            verify(carService, times(1)).updateCar(carId, requestDTO);

            assertAll(
                    () -> assertEquals(expectedResponseDTO.getIdCar(), responseDTO.getIdCar()),
                    () -> assertEquals(expectedResponseDTO.getBrand(), responseDTO.getBrand()),
                    () -> assertEquals(expectedResponseDTO.getModel(), responseDTO.getModel())
            );
        }

    @Test
    void deleteCar()throws CarNotFoundException {

        Long carId = 1L;

        doNothing().when(carService).deleteCar(carId);

        ResponseEntity<String> responseEntity = carController.deleteCar(carId);

        verify(carService, times(1)).deleteCar(carId);

        assertAll(
                () -> assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode()),
                () -> assertEquals("Car deleted successfully", responseEntity.getBody())
        );
    }
}