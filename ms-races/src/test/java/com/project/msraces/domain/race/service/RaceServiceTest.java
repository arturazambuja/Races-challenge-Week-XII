package com.project.msraces.domain.race.service;

import com.project.msraces.domain.race.dtos.RaceRequestDTO;
import com.project.msraces.domain.race.dtos.RaceResponseDTO;
import com.project.mscars.domain.car.model.Car;
import com.project.msraces.domain.race.exceptions.RaceNotFoundException;
import com.project.msraces.domain.race.model.Race;
import com.project.msraces.domain.race.repository.RaceRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class RaceServiceTest {
    @Spy
    private ModelMapper modelMapper;
    @InjectMocks
    private RaceService raceService;
    @Mock
    private RaceRepository raceRepository;

    @Test
    void convertRaceToResponseDTO() {
    }

    @Test
    void createRace() {
        Race raceToSave = new Race();
        raceToSave.setIdRace(1L);
        raceToSave.setName("Sample Race");

        when(raceRepository.save(any(Race.class))).thenReturn(raceToSave);

        RaceRequestDTO requestDTO = new RaceRequestDTO();
        requestDTO.setName("Sample Race");

        RaceResponseDTO responseDTO = raceService.createRace(requestDTO);

        assertEquals(1L, responseDTO.getIdRace());
        assertEquals("Sample Race", responseDTO.getName());
    }

    @Test
    void getAllRaces() {
        Race race1 = new Race();
        race1.setIdRace(1L);
        race1.setName("Race 1");

        Race race2 = new Race();
        race2.setIdRace(2L);
        race2.setName("Race 2");

        when(raceRepository.findAll()).thenReturn(Arrays.asList(race1, race2));

        List<RaceResponseDTO> responseDTOs = raceService.getAllRaces();

        assertEquals(2, responseDTOs.size());
        assertEquals("Race 1", responseDTOs.get(0).getName());
        assertEquals("Race 2", responseDTOs.get(1).getName());
    }

    @Test
    void getRaceById() throws RaceNotFoundException {

        Race race = new Race();
        race.setIdRace(1L);
        race.setName("Race 1");

        when(raceRepository.findById(1L)).thenReturn(Optional.of(race));
        when(raceRepository.findById(2L)).thenReturn(Optional.empty());

        RaceResponseDTO responseDTO = raceService.getRaceById(1L);

        assertEquals(1L, responseDTO.getIdRace());
        assertEquals("Race 1", responseDTO.getName());

        assertThrows(RaceNotFoundException.class, () -> raceService.getRaceById(2L));
    }

    @Test
    void updateRace() throws RaceNotFoundException {

        Race existingRace = new Race();
        existingRace.setIdRace(1L);
        existingRace.setName("Race 1");

        when(raceRepository.findById(1L)).thenReturn(Optional.of(existingRace));
        when(raceRepository.save(any(Race.class))).thenAnswer(invocation -> invocation.getArgument(0));

        RaceRequestDTO updateRequestDTO = new RaceRequestDTO();
        updateRequestDTO.setName("Updated Race Name");

        RaceResponseDTO updatedRaceDTO = raceService.updateRace(1L, updateRequestDTO);

        assertEquals(1L, updatedRaceDTO.getIdRace());
        assertEquals("Updated Race Name", updatedRaceDTO.getName());

        verify(raceRepository).save(existingRace);

        assertThrows(RaceNotFoundException.class, () -> raceService.updateRace(2L, updateRequestDTO));
    }

    @Test
    void deleteRace() throws RaceNotFoundException {

        Race existingRace = new Race();
        existingRace.setIdRace(1L);
        existingRace.setName("Race 1");

        when(raceRepository.findById(1L)).thenReturn(Optional.of(existingRace));

        raceService.deleteRace(1L);

        verify(raceRepository).delete(existingRace);

        assertThrows(RaceNotFoundException.class, () -> raceService.deleteRace(2L));
    }

    @Test
    void addCarsToRace() throws RaceNotFoundException {

        Race existingRace = new Race();
        existingRace.setIdRace(1L);
        existingRace.setName("Race 1");

        existingRace.setCars(new ArrayList<>());

        when(raceRepository.findById(1L)).thenReturn(Optional.of(existingRace));
        when(raceRepository.save(any())).thenReturn(existingRace);

        List<Car> carsToAdd = new ArrayList<>();
        Car car1 = new Car();
        car1.setIdCar(101L);
        Car car2 = new Car();
        car2.setIdCar(102L);
        carsToAdd.add(car1);
        carsToAdd.add(car2);

        RaceResponseDTO responseDTO = raceService.addCarsToRace(1L, carsToAdd);

        assertTrue(existingRace.getCars().contains(car1));
        assertTrue(existingRace.getCars().contains(car2));

        verify(raceRepository).save(existingRace);

        assertEquals(existingRace.getIdRace(), responseDTO.getIdRace());
        assertEquals(existingRace.getName(), responseDTO.getName());

        assertThrows(RaceNotFoundException.class, () -> raceService.addCarsToRace(2L, carsToAdd));
    }

    @Test
    void sortPositions() {
    }
}