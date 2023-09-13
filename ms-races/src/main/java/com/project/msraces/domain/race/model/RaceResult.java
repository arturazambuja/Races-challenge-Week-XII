package com.project.msraces.domain.race.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RaceResult {
    @Id
    private Long idCar;
    private int position;
    @ManyToOne
    private Race race;

    public RaceResult(long idCar, Integer integer) {
    }
}
