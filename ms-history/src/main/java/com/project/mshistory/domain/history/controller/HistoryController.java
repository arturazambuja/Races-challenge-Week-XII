package com.project.mshistory.domain.history.controller;

import com.project.mshistory.domain.history.dtos.HistoryResponseDTO;
import com.project.mshistory.domain.history.exceptions.HistoryNotFoundException;
import com.project.mshistory.domain.history.model.History;
import com.project.mshistory.domain.history.repository.HistoryRepository;
import com.project.mshistory.domain.history.service.HistoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/history")
public class HistoryController {

    private final HistoryService historyService;

    public HistoryController(HistoryService historyService) {
        this.historyService = historyService;
    }
    @GetMapping
    public List<HistoryResponseDTO> getAllHistory() {
        return historyService.getAllHistory();
    }
    @GetMapping("/{idHistory}")
    public HistoryResponseDTO getHistoryById(@PathVariable Long idHistory) throws HistoryNotFoundException {
        return historyService.getHistoryById(idHistory);
    }
}
