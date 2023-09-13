package com.project.msraces.domain.track.model;

import com.project.msraces.domain.race.model.Race;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Builder
@Data
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Tracks")
public class Track {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_track", unique = true, nullable = false)
    private long idTrack;
    @Column(name = "name", unique = true, nullable = false)
    private String name;
    @Column(name = "country", nullable = false)
    private String country;
    @Column(name = "date")
    @DateTimeFormat(pattern = "yyyy/mm/dd")
    private Date date;
    @OneToOne
    private Race race;
}
