package com.example.Sorting.service;


import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Collections;

@Service
public class SortingService {

    // Select sorting algorithm based on user input
    public List<Integer> sortArray(String algorithm, List<Integer> array) {
        switch (algorithm.toLowerCase()) {
            case "bubble":
                return bubbleSort(array);
            case "selection":
                return selectionSort(array);
            case "insertion":
                return insertionSort(array);
            case "merge":
                return mergeSort(array);
            case "quick":
                return quickSort(array);
            default:
                throw new IllegalArgumentException("Invalid sorting algorithm");
        }
    }

    // ✅ Bubble Sort
    private List<Integer> bubbleSort(List<Integer> array) {
        List<Integer> arr = array.stream().collect(Collectors.toList());
        int n = arr.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr.get(j) > arr.get(j + 1)) {
                    Collections.swap(arr, j, j + 1);
                }
            }
        }
        return arr;
    }

    // ✅ Selection Sort
    private List<Integer> selectionSort(List<Integer> array) {
        List<Integer> arr = array.stream().collect(Collectors.toList());
        int n = arr.size();
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (arr.get(j) < arr.get(minIndex)) {
                    minIndex = j;
                }
            }
            Collections.swap(arr, i, minIndex);
        }
        return arr;
    }

    // ✅ Insertion Sort
    private List<Integer> insertionSort(List<Integer> array) {
        List<Integer> arr = array.stream().collect(Collectors.toList());
        int n = arr.size();
        for (int i = 1; i < n; i++) {
            int key = arr.get(i);
            int j = i - 1;
            while (j >= 0 && arr.get(j) > key) {
                arr.set(j + 1, arr.get(j));
                j--;
            }
            arr.set(j + 1, key);
        }
        return arr;
    }

    // ✅ Merge Sort
    private List<Integer> mergeSort(List<Integer> array) {
        if (array.size() <= 1) {
            return array;
        }
        int mid = array.size() / 2;
        List<Integer> left = mergeSort(array.subList(0, mid));
        List<Integer> right = mergeSort(array.subList(mid, array.size()));
        return merge(left, right);
    }

    private List<Integer> merge(List<Integer> left, List<Integer> right) {
        List<Integer> merged = left.stream().collect(Collectors.toList());
        merged.addAll(right);
        Collections.sort(merged);
        return merged;
    }

    // ✅ Quick Sort
    private List<Integer> quickSort(List<Integer> array) {
        if (array.size() <= 1) {
            return array;
        }
        int pivot = array.get(array.size() / 2);
        List<Integer> left = array.stream().filter(n -> n < pivot).collect(Collectors.toList());
        List<Integer> middle = array.stream().filter(n -> n == pivot).collect(Collectors.toList());
        List<Integer> right = array.stream().filter(n -> n > pivot).collect(Collectors.toList());

        List<Integer> sorted = quickSort(left);
        sorted.addAll(middle);
        sorted.addAll(quickSort(right));
        return sorted;
    }
}