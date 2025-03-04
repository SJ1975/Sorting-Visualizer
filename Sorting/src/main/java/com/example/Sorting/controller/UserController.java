package com.example.Sorting.controller;

import com.example.Sorting.model.User;
import com.example.Sorting.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    // Create a new user
    @PostMapping("/create")
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    // Get user by ID
    @GetMapping("/{userId}")
    public Optional<User> getUserById(@PathVariable String userId) {
        return userService.getUserById(userId);
    }

    // Get user by username
    @GetMapping("/username/{username}")
    public User getUserByUsername(@PathVariable String username) {
        return userService.getUserByUsername(username);
    }
}
