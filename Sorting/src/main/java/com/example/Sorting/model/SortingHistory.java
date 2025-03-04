package com.example.Sorting.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Data
@Document(collection = "sortingHistory")
public class SortingHistory {
    @Id
    private String id;
    private String userId;
    private String algorithm;
    private List<Integer> inputArray;
    private List<Integer> sortedArray;
    private long executionTime;
}
