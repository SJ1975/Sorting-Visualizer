package com.example.Sorting.repository;

import com.example.Sorting.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    // ✅ Add this method to find a user by email
    Optional<User> findByEmail(String email);
}