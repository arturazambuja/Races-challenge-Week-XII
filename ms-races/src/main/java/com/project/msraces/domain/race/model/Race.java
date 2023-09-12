package com.project.msraces.domain.race.model;

import com.project.msraces.domain.track.model.Track;
import jakarta.persistence.*;
import lombok.*;

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
    @OneToOne
    private Track track;
}
