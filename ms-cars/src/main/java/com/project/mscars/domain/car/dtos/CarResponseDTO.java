package com.project.mscars.domain.car.dtos;

import com.project.mscars.domain.car.model.Pilot;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarResponseDTO that = (CarResponseDTO) o;
        return Objects.equals(brand, that.brand) &&
                Objects.equals(model, that.model) &&
                Objects.equals(year, that.year);
    }
    @Override
    public int hashCode() {
        return Objects.hash(brand, model, year);
    }
}
