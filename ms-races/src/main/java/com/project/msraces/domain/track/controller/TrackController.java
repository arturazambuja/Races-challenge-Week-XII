package com.project.msraces.domain.track.controller;

import com.project.msraces.domain.track.dtos.TrackRequestDTO;
import com.project.msraces.domain.track.dtos.TrackResponseDTO;
import com.project.msraces.domain.track.exceptions.TrackNotFoundException;
import com.project.msraces.domain.track.service.TrackService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/tracks")
public class TrackController {

    private final TrackService trackService;

    public TrackController(TrackService trackService) {
        this.trackService = trackService;
    }
    @GetMapping
    public List<TrackResponseDTO> getAllTracks() {
        return trackService.getAllTracks();
    }
    @GetMapping("/{idTrack}")
    public TrackResponseDTO getTrackById(@PathVariable Long idTrack) throws TrackNotFoundException {
        return trackService.getTrackById(idTrack);
    }
    @PostMapping
    public TrackResponseDTO createTrack(@RequestBody TrackRequestDTO requestDTO) {
        return trackService.createTrack(requestDTO);
    }
}
