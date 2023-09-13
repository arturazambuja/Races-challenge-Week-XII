package com.project.mshistory.domain.history.service;

import com.project.mshistory.domain.history.dtos.HistoryResponseDTO;
import com.project.mshistory.domain.history.exceptions.HistoryNotFoundException;
import com.project.mshistory.domain.history.model.History;
import com.project.mshistory.domain.history.repository.HistoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HistoryService {
    private final HistoryRepository historyRepository;
    private final ModelMapper modelMapper;

    public HistoryService(HistoryRepository historyRepository, ModelMapper modelMapper) {
        this.historyRepository = historyRepository;
        this.modelMapper = modelMapper;
    }

    public HistoryResponseDTO convertHistoryToResponseDTO(History history) {
        return modelMapper.map(history, HistoryResponseDTO.class);
    }
    public List<HistoryResponseDTO> getAllHistory() {
        List<History> history = historyRepository.findAll();
        return history.stream()
                .map(this::convertHistoryToResponseDTO)
                .collect(Collectors.toList());
    }
    public HistoryResponseDTO getHistoryById(Long idHistory) throws HistoryNotFoundException {
        History history = historyRepository.findById(idHistory)
                .orElseThrow(() -> new HistoryNotFoundException("History not found with this id"));
        return convertHistoryToResponseDTO(history);
    }
}
