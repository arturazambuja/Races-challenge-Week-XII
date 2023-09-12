package com.project.msraces.domain.track.service;

import com.project.msraces.domain.track.dtos.TrackRequestDTO;
import com.project.msraces.domain.track.dtos.TrackResponseDTO;
import com.project.msraces.domain.track.exceptions.TrackNotFoundException;
import com.project.msraces.domain.track.model.Track;
import com.project.msraces.domain.track.repository.TrackRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrackService {

    private final ModelMapper modelMapper;
    private final TrackRepository trackRepository;
    public TrackService(ModelMapper modelMapper, TrackRepository trackRepository) {
        this.modelMapper = modelMapper;
        this.trackRepository = trackRepository;
    }
    public TrackResponseDTO convertTrackToResponseDTO(Track track) {
        return modelMapper.map(track, TrackResponseDTO.class);
    }
    public TrackResponseDTO createTrack(TrackRequestDTO requestDTO) {

        Track track = new Track();
        track.setName(requestDTO.getName());
        track.setCountry(requestDTO.getCountry());
        track.setDate(requestDTO.getDate());

        Track savedTrack = trackRepository.save(track);

        return convertTrackToResponseDTO(savedTrack);
    }
    public List<TrackResponseDTO> getAllTracks() {
        List<Track> tracks = trackRepository.findAll();
        return tracks.stream()
                .map(this::convertTrackToResponseDTO)
                .collect(Collectors.toList());
    }
    public TrackResponseDTO getTrackById(Long idTrack) throws TrackNotFoundException {
        Track track = trackRepository.findById(idTrack)
                .orElseThrow(() -> new TrackNotFoundException("Track not found with this id"));
        return convertTrackToResponseDTO(track);
    }
}
