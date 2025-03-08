package com.example.Sorting.controller;

import com.example.Sorting.service.SortingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/api/sorting")
@CrossOrigin("*")
public class SortingController {

    @Autowired
    private SortingService sortingService;

    // ✅ Sort an array using the selected algorithm
    @PostMapping("/{algorithm}")
    public Map<String, Object> sortArray(@RequestBody Map<String, Object> request) {
        String algorithm = (String) request.get("algorithm");
        List<Integer> originalArray = (List<Integer>) request.get("array");

        long startTime = System.nanoTime();
        List<Integer> sortedArray = sortingService.sortArray(algorithm, originalArray);
        long endTime = System.nanoTime();
        long timeTaken = (endTime - startTime) / 1_000_000; // Convert to milliseconds

        // ✅ Prepare response
        Map<String, Object> response = new HashMap<>();
        response.put("algorithm", algorithm);
        response.put("originalArray", originalArray);
        response.put("sortedArray", sortedArray);
        response.put("timeTaken", timeTaken + " ms");

        return response;
    }


}
