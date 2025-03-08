package com.example.Sorting.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Data
@Document(collection = "sorting_history")
public class SortingHistory {
    @Id
    private String id;
    private String algorithm;
    private List<Integer> originalArray;
    private List<Integer> sortedArray;
    private long timeTaken;
    private String timestamp;
}
