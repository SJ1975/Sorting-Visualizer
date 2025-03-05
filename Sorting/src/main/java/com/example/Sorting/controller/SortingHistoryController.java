package com.example.Sorting.controller;

import com.example.Sorting.model.SortingHistory;
import com.example.Sorting.repository.SortingHistoryRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/history")
//@CrossOrigin("*")
public class SortingHistoryController {

    private final SortingHistoryRepository historyRepository;

    public SortingHistoryController(SortingHistoryRepository historyRepository) {
        this.historyRepository = historyRepository;
    }

    // ✅ Save sorting history
    @PostMapping("/save")
    public SortingHistory saveSortingHistory(@RequestBody SortingHistory history) {
        history.setTimestamp(new Date());  // Set timestamp
        SortingHistory savedHistory = historyRepository.save(history);
        System.out.println("✅ Sorting history saved: " + savedHistory); // ✅ Log data
        return savedHistory;
    }
    // ✅ Get sorting history for a user
    @GetMapping("/{userId}")
    public List<SortingHistory> getHistoryByUser(@PathVariable String userId) {
        return historyRepository.findByUserId(userId);
    }
}

