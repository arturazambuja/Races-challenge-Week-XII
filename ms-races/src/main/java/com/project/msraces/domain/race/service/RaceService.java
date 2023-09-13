package com.project.msraces.domain.race.service;

import com.project.mscars.domain.car.model.Car;
import com.project.msraces.config.CarFeignClient;
import com.project.msraces.domain.race.dtos.RaceRequestDTO;
import com.project.msraces.domain.race.dtos.RaceResponseDTO;
import com.project.msraces.domain.race.exceptions.RaceNotFoundException;
import com.project.msraces.domain.race.model.Race;
import com.project.msraces.domain.race.model.RaceResult;
import com.project.msraces.domain.race.repository.RaceRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RaceService {
    private final ModelMapper modelMapper;
    private final RaceRepository raceRepository;
    private CarFeignClient carFeignClient;
    public RaceService(ModelMapper modelMapper,RaceRepository raceRepository) {
        this.modelMapper = modelMapper;
        this.raceRepository = raceRepository;
    }
    public RaceResponseDTO convertRaceToResponseDTO(Race race) {
        return modelMapper.map(race, RaceResponseDTO.class);
    }
    public RaceResponseDTO createRace(RaceRequestDTO requestDTO) {

        Race race = new Race();
        race.setName(requestDTO.getName());

        Race savedRace = raceRepository.save(race);

        return convertRaceToResponseDTO(savedRace);
    }
    public List<RaceResponseDTO> getAllRaces() {
        List<Race> races = raceRepository.findAll();
        return races.stream()
                .map(this::convertRaceToResponseDTO)
                .collect(Collectors.toList());
    }
    public RaceResponseDTO getRaceById(Long idRace) throws RaceNotFoundException {
        Race race = raceRepository.findById(idRace)
                .orElseThrow(() -> new RaceNotFoundException("Race not found with this id"));
        return convertRaceToResponseDTO(race);
    }
    public RaceResponseDTO updateRace(Long idRace, RaceRequestDTO requestDTO) throws RaceNotFoundException {
        Race race = raceRepository.findById(idRace)
                .orElseThrow(() -> new RaceNotFoundException("Race not found with this id"));

        race.setName(requestDTO.getName());

        raceRepository.save(race);
        return convertRaceToResponseDTO(race);
    }
    public void deleteRace(Long idCRace) throws RaceNotFoundException {
        Race race = raceRepository.findById(idCRace)
                .orElseThrow(() -> new RaceNotFoundException("Race not found with this id"));

        raceRepository.delete(race);
    }
    public RaceResponseDTO addCarsToRace(Long raceId, List<Car> cars) throws RaceNotFoundException {

        Race race = raceRepository.findById(raceId)
                .orElseThrow(() -> new RaceNotFoundException("Race not found with ID: " + raceId));

        race.getCars().addAll(cars);
        sortPositions(cars);
        return convertRaceToResponseDTO(raceRepository.save(race));
    }
    public void sortPositions(List<Car> cars) {
        List<RaceResult> result = new ArrayList<>();
        List<Integer> positions = new ArrayList<>();

        for (int i = 1; i <= cars.size(); i++) {
            positions.add(i);
        }

        Collections.shuffle(positions);

        int index = 0;
        for (Car car : cars) {
            result.add(new RaceResult(car.getIdCar(), positions.get(index)));
            index++;
        }

    }
}
