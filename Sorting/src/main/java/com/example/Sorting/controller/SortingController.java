package com.example.Sorting.controller;


import com.example.Sorting.model.SortingHistory;
import com.example.Sorting.model.SortingRequest;
import com.example.Sorting.repository.SortingHistoryRepository;
import com.example.Sorting.service.SortingService;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/sorting")
@CrossOrigin(origins = "http://localhost:3000") //Allow frontend access
public class SortingController {
    private final SortingService sortingService;

    public SortingController(SortingService sortingService) {
        this.sortingService = sortingService;
    }

    @PostMapping("/{algorithm}")
    public int[] sort(@PathVariable String algorithm, @RequestBody SortingRequest request) {
        switch (algorithm) {
            case "bubble":
                return sortingService.bubbleSort(request.getArray());
            case "selection":
                return sortingService.selectionSort(request.getArray());
            case "insertion":
                return sortingService.insertionSort(request.getArray());
            case "merge":
                return sortingService.mergeSort(request.getArray());
            case "quick":
                return sortingService.quickSort(request.getArray());
            default:
                throw new IllegalArgumentException("Invalid sorting algorithm");
        }
    }
}
