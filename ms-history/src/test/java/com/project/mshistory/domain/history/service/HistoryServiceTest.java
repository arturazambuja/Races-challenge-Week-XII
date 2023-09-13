package com.project.mshistory.domain.history.service;

import com.project.mshistory.domain.history.dtos.HistoryResponseDTO;
import com.project.mshistory.domain.history.exceptions.HistoryNotFoundException;
import com.project.mshistory.domain.history.model.History;
import com.project.mshistory.domain.history.repository.HistoryRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class HistoryServiceTest {
    @Spy
    private ModelMapper modelMapper;
    @InjectMocks
    private HistoryService historyService;
    @Mock
    private HistoryRepository historyRepository;

    @Test
    void convertHistoryToResponseDTO() {
    }

    @Test
    void getAllHistory() {
        List<History> historyList = Arrays.asList(
                new History(1L, "Event 1"),
                new History(2L, "Event 2"),
                new History(3L, "Event 3")
        );

        when(historyRepository.findAll()).thenReturn(historyList);

        List<HistoryResponseDTO> historyDTOList = historyService.getAllHistory();

        assertEquals(historyList.size(), historyDTOList.size());

        for (int i = 0; i < historyList.size(); i++) {
            History history = historyList.get(i);
            HistoryResponseDTO historyDTO = historyDTOList.get(i);

            assertEquals(history.getIdHistory(), historyDTO.getIdHistory());
            assertEquals(history.getName(), historyDTO.getName());
        }
    }

    @Test
    void getHistoryById() throws HistoryNotFoundException {

        History history = new History();
        history.setIdHistory(1L);
        history.setName("Sample History");

        Mockito.when(historyRepository.findById(1L)).thenReturn(Optional.of(history));

        HistoryResponseDTO responseDTO = historyService.getHistoryById(1L);

        assertEquals(1L, responseDTO.getIdHistory());
        assertEquals("Sample History", responseDTO.getName());
    }

    @Test
    public void testGetHistoryById_NotFound() {

        Mockito.when(historyRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(HistoryNotFoundException.class, () -> {
            historyService.getHistoryById(1L);
        });
    }
}