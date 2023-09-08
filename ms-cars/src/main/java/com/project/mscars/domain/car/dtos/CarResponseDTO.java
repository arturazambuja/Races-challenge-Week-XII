package com.project.mscars.domain.car.dtos;

import com.project.mscars.domain.car.model.Pilot;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CarResponseDTO {
    private long idCar;
    private String brand;
    private String model;
    private Pilot pilot;
    private Date year;
}
