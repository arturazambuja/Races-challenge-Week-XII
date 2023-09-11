package com.project.msraces.domain.track.dtos;

import com.project.msraces.domain.race.model.Race;
import lombok.*;

import java.util.Date;

@Builder
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TrackResponseDTO {
    private long idTrack;
    private String name;
    private String country;
    private Date date;
    private Race race;
}
