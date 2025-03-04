package com.example.Sorting.controller;


import com.example.Sorting.service.SortingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sort")
public class SortingController {
    @Autowired
    private SortingService sortingService;

    @PostMapping("/bubble")
    public int[] bubbleSort(@RequestParam String userId, @RequestBody int[] arr) {
        return sortingService.bubbleSort(arr, userId);
    }
}
