package com.project.mshistory.domain.history.repository;

import com.project.mshistory.domain.history.model.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoryRepository extends JpaRepository<Long, History> {
}
