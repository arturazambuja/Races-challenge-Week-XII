package com.project.msraces.domain.race.model;

import com.project.msraces.domain.track.model.Track;
import com.project.mscars.domain.car.model.Car;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Builder
@Data
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Races")
public class Race {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_race")
    private long idRace;
    @Column(name = "name")
    private String name;
    @Column(name = "result")
    private String result;
    @Transient
    @ManyToMany(mappedBy = "races")
    private List<Car> cars;
    @OneToOne
    private Track track;
}
