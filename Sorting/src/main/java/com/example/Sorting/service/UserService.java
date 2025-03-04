package com.example.Sorting.service;

import com.example.Sorting.model.User;
import com.example.Sorting.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    // Create a new user
    public User createUser(User user) {
        return userRepository.save(user);
    }

    // Fetch a user by ID
    public Optional<User> getUserById(String userId) {
        return userRepository.findById(userId);
    }

    // Fetch a user by username
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
