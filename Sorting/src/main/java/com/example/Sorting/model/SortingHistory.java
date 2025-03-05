package com.example.Sorting.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document(collection = "sorting_history")
public class SortingHistory {
    @Id
    private String id;
    private String userId;
    private String algorithm;
    private int[] sortedArray;
    private Date timestamp;
}
