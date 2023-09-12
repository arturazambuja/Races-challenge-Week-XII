package com.project.msraces.domain.race.controller;

import com.project.msraces.domain.race.dtos.RaceRequestDTO;
import com.project.msraces.domain.race.dtos.RaceResponseDTO;
import com.project.msraces.domain.race.exceptions.RaceNotFoundException;
import com.project.msraces.domain.race.service.RaceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/races")
public class RaceController {
    private final RaceService raceService;
    public RaceController(RaceService raceService) {
        this.raceService = raceService;
    }
    @GetMapping
    public List<RaceResponseDTO> getAllRaces() {
        return raceService.getAllRaces();
    }
    @GetMapping("/{idRace}")
    public RaceResponseDTO getCoordinatorById(@PathVariable Long idRace) throws RaceNotFoundException {
        return raceService.getRaceById(idRace);
    }
    @PostMapping
    public RaceResponseDTO createRace(@RequestBody RaceRequestDTO requestDTO) {
        return raceService.createRace(requestDTO);
    }
}
