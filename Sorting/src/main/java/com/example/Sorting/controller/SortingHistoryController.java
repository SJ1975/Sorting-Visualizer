package com.example.Sorting.controller;

import com.example.Sorting.model.SortingHistory;
import com.example.Sorting.repository.SortingHistoryRepository;
import com.example.Sorting.service.SortingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/api/history")
@CrossOrigin("*")
public class SortingHistoryController {

    @Autowired
    private SortingHistoryRepository historyRepository;

    @Autowired
    private SortingService sortingService;

    // ✅ Save sorting history (Auto Sort & Store)
    @PostMapping("/save")
    public ResponseEntity<SortingHistory> saveSortingHistory(@RequestBody SortingHistory request) {
        long startTime = System.nanoTime(); // Track time

        // Apply sorting based on algorithm type
        List<Integer> sortedArray = sortingService.sortArray(request.getAlgorithm(), request.getOriginalArray());

        long endTime = System.nanoTime();
        long timeTaken = (endTime - startTime) / 1_000_000; // Convert to milliseconds

        // Save sorting result
        SortingHistory sortingHistory = new SortingHistory();
        sortingHistory.setAlgorithm(request.getAlgorithm());
        sortingHistory.setOriginalArray(request.getOriginalArray());
        sortingHistory.setSortedArray(sortedArray);
        sortingHistory.setTimeTaken(timeTaken);
        sortingHistory.setTimestamp(Instant.now().toString());

        // ✅ Make sure sortingHistoryRepository is properly injected
        SortingHistory savedHistory = historyRepository.save(sortingHistory);

        return ResponseEntity.ok(savedHistory);
    }

    // ✅ Fetch all sorting history
    @GetMapping("/all")
    public List<SortingHistory> getAllSortingHistory() {
        return historyRepository.findAll();
    }

    // ✅ Fetch sorting history based on algorithm type
    @GetMapping("/history/{algorithm}")
    public List<SortingHistory> getHistoryByAlgorithm(@PathVariable String algorithm) {
        return historyRepository.findByAlgorithm(algorithm);
    }

}

