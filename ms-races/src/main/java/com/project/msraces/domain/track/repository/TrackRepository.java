package com.project.msraces.domain.track.repository;

import com.project.msraces.domain.track.model.Track;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrackRepository extends JpaRepository<Track, Long> {
}
