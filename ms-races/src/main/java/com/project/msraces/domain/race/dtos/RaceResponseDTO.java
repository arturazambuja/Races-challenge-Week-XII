package com.project.msraces.domain.race.dtos;

import com.project.msraces.domain.track.model.Track;
import jakarta.persistence.Column;
import lombok.*;

@Builder
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RaceResponseDTO {
    private long idRace;
    private String name;
    private String result;
    private Track track;
}
