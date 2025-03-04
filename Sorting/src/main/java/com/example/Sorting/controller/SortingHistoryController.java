package com.example.Sorting.controller;

import com.example.Sorting.model.SortingHistory;
import com.example.Sorting.repository.SortingHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/history")
public class SortingHistoryController {
    @Autowired
    private SortingHistoryRepository historyRepository;

    @GetMapping("/{userId}")
    public List<SortingHistory> getUserHistory(@PathVariable String userId) {
        return historyRepository.findByUserId(userId);
    }
}

