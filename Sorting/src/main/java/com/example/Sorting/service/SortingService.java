package com.example.Sorting.service;


import com.example.Sorting.model.SortingHistory;
import com.example.Sorting.repository.SortingHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Arrays;

@Service
public class SortingService {
    @Autowired
    private SortingHistoryRepository historyRepository;

    public int[] bubbleSort(int[] arr, String userId) {
        long startTime = System.nanoTime();

        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }

        long endTime = System.nanoTime();
        long executionTime = endTime - startTime;

        // Convert int[] to List<Integer>
        List<Integer> inputList = Arrays.stream(arr).boxed().collect(Collectors.toList());
        List<Integer> sortedList = Arrays.stream(arr).boxed().collect(Collectors.toList());

        // Save sorting history in MongoDB
        SortingHistory history = new SortingHistory();
        history.setUserId(userId);
        history.setAlgorithm("Bubble Sort");
        history.setInputArray(inputList);
        history.setSortedArray(sortedList);
        history.setExecutionTime(executionTime);

        historyRepository.save(history);
        return arr;
    }
}
