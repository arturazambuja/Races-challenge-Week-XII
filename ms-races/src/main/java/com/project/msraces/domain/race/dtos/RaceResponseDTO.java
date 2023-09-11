package com.project.msraces.domain.race.dtos;

import com.project.msraces.domain.track.model.Track;
import lombok.*;

@Builder
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RaceResponseDTO {
    private long idRace;
    private Track track;
}
