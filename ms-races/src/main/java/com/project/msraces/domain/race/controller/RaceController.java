package com.project.msraces.domain.race.controller;

import com.project.mscars.domain.car.exceptions.CarNotFoundException;
import com.project.mscars.domain.car.model.Car;
import com.project.msraces.config.CarFeignClient;
import com.project.msraces.domain.race.dtos.RaceRequestDTO;
import com.project.msraces.domain.race.dtos.RaceResponseDTO;
import com.project.msraces.domain.race.exceptions.RaceNotFoundException;
import com.project.msraces.domain.race.repository.RaceRepository;
import com.project.msraces.domain.race.service.RaceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/races")
public class RaceController{
    private final RaceService raceService;
    private CarFeignClient carFeignClient;
    private final RaceRepository raceRepository;
    public RaceController(RaceService raceService, RaceRepository raceRepository) {
        this.raceService = raceService;
        this.raceRepository = raceRepository;
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
    @PutMapping("/{idRace}")
    public RaceResponseDTO updateRace(@PathVariable Long idRace, @RequestBody RaceRequestDTO requestDTO) throws RaceNotFoundException {
        return raceService.updateRace(idRace, requestDTO);
    }
    @DeleteMapping("/{idRace}")
    public void deleteRace(@PathVariable Long idRace) throws RaceNotFoundException {
        raceService.deleteRace(idRace);
    }
    @PostMapping("/{idRace}/addCars")
    public ResponseEntity<String> addCarsToRace(@PathVariable Long idRace, @RequestBody List<Long> carIds) throws RaceNotFoundException, CarNotFoundException {

        if (carIds.size() < 3 || carIds.size() > 10) {
            return ResponseEntity.badRequest().body("Insufficient Number of cars, must have at least 3 and maximum of 10 cars.");
        }
        List<Car> cars = carFeignClient.getCarsByIds(carIds);

        if (cars == null || cars.isEmpty()) {
            return ResponseEntity.badRequest().body("No car found with this Id.");
        }

        RaceResponseDTO race = raceService.addCarsToRace(idRace, cars);

        return ResponseEntity.ok("Cars added to the race.");
    }
}
