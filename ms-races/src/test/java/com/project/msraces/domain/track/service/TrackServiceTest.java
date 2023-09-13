package com.project.msraces.domain.track.service;

import com.project.mscars.domain.car.model.Car;
import com.project.msraces.domain.race.dtos.RaceResponseDTO;
import com.project.msraces.domain.race.exceptions.RaceNotFoundException;
import com.project.msraces.domain.race.model.Race;
import com.project.msraces.domain.race.repository.RaceRepository;
import com.project.msraces.domain.race.service.RaceService;
import com.project.msraces.domain.track.dtos.TrackRequestDTO;
import com.project.msraces.domain.track.dtos.TrackResponseDTO;
import com.project.msraces.domain.track.exceptions.TrackNotFoundException;
import com.project.msraces.domain.track.model.Track;
import com.project.msraces.domain.track.repository.TrackRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class TrackServiceTest {

    @Spy
    private ModelMapper modelMapper;
    @InjectMocks
    private TrackService trackService;
    @Mock
    private RaceRepository raceRepository;
    @Mock
    private RaceService raceService;
    @Mock
    private TrackRepository trackRepository;
    @Test
    void convertTrackToResponseDTO() {
    }

    @Test
    void createTrack() {

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
    }

    @Test
    void getAllTracks() {
        MockitoAnnotations.initMocks(this);

        Track track2 = new Track();
        track2.setIdTrack(2L);
        track2.setName("Track 2");

        List<Track> sampleTracks = Arrays.asList(track2, track2);

        when(trackRepository.findAll()).thenReturn(sampleTracks);

        List<TrackResponseDTO> trackResponseDTOs = trackService.getAllTracks();
    }

    @Test
    void getTrackById() throws TrackNotFoundException {

        MockitoAnnotations.initMocks(this);

        Track sampleTrack = new Track();
        sampleTrack.setIdTrack(1L);
        sampleTrack.setName("Sample Track");

        when(trackRepository.findById(1L)).thenReturn(Optional.of(sampleTrack));

        when(trackRepository.findById(2L)).thenReturn(Optional.empty());
        assertThrows(TrackNotFoundException.class, () -> trackService.getTrackById(2L));
    }

    @Test
    void updateTrack() throws TrackNotFoundException {

        Long idTrack = 1L;
        TrackRequestDTO requestDTO = new TrackRequestDTO();
        requestDTO.setName("Updated Name");
        requestDTO.setCountry("Updated Country");

        Track existingTrack = new Track();
        existingTrack.setIdTrack(idTrack);
        existingTrack.setName("Original Name");
        existingTrack.setCountry("Original Country");

        when(trackRepository.findById(idTrack)).thenReturn(java.util.Optional.of(existingTrack));
        when(trackRepository.save(any(Track.class))).thenAnswer(invocation -> invocation.getArgument(0));

        TrackResponseDTO updatedTrack = trackService.updateTrack(idTrack, requestDTO);

        assertNotNull(updatedTrack);
        assertEquals(idTrack, updatedTrack.getIdTrack());
        assertEquals(requestDTO.getName(), updatedTrack.getName());
        assertEquals(requestDTO.getCountry(), updatedTrack.getCountry());
    }

    @Test
    public void testUpdateTrackTrackNotFoundException() {

        Long idTrack = 1L;
        TrackRequestDTO requestDTO = new TrackRequestDTO();
        requestDTO.setName("Updated Name");
        requestDTO.setCountry("Updated Country");

        when(trackRepository.findById(idTrack)).thenReturn(java.util.Optional.empty());

        assertThrows(TrackNotFoundException.class, () -> trackService.updateTrack(idTrack, requestDTO));
    }

    @Test
    void deleteTrack() throws TrackNotFoundException {

        Long idTrack = 1L;
        Track existingTrack = new Track();
        existingTrack.setIdTrack(idTrack);

        when(trackRepository.findById(idTrack)).thenReturn(java.util.Optional.of(existingTrack));

        assertDoesNotThrow(() -> trackService.deleteTrack(idTrack));

        verify(trackRepository, times(1)).delete(existingTrack);
    }

    @Test
    public void testDeleteTrackTrackNotFoundException() {

        Long idTrack = 1L;

        when(trackRepository.findById(idTrack)).thenReturn(java.util.Optional.empty());

        assertThrows(TrackNotFoundException.class, () -> trackService.deleteTrack(idTrack));
    }
}