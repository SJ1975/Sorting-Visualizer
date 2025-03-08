package com.example.Sorting.service;

import com.example.Sorting.model.User;
import com.example.Sorting.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // ✅ Find User by Email
    public Optional<User> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // ✅ Save New User
    public User saveUser(User user) {
        return userRepository.save(user);
    }
}