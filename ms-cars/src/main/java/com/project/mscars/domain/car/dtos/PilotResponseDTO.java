package com.project.mscars.domain.car.dtos;

import com.project.mscars.domain.car.model.Car;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PilotResponseDTO {
    private long idPilot;
    private String name;
    private int age;
    private Car car;
}
