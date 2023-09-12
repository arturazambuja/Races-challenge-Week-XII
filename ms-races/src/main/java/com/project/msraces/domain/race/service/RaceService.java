package com.project.msraces.domain.race.service;

import com.project.msraces.domain.race.dtos.RaceRequestDTO;
import com.project.msraces.domain.race.dtos.RaceResponseDTO;
import com.project.msraces.domain.race.exceptions.RaceNotFoundException;
import com.project.msraces.domain.race.model.Race;
import com.project.msraces.domain.race.repository.RaceRepository;
import com.project.msraces.domain.track.dtos.TrackResponseDTO;
import com.project.msraces.domain.track.model.Track;
import com.project.msraces.domain.track.repository.TrackRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RaceService {
    private final ModelMapper modelMapper;
    private final RaceRepository raceRepository;
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
}
