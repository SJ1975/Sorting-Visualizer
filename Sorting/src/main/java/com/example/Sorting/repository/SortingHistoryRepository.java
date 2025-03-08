package com.example.Sorting.repository;

import com.example.Sorting.model.SortingHistory;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface SortingHistoryRepository extends MongoRepository<SortingHistory, String> {
//    List<SortingHistory> findByUserId(String userId); // ✅ Fetch history by userId
    List<SortingHistory> findByAlgorithm(String algorithm); // ✅ Fetch history by sorting algorithm
}
