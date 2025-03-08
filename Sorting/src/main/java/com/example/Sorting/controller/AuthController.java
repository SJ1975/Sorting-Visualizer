//package com.example.Sorting.controller;
//
//import com.example.Sorting.model.User;
//import com.example.Sorting.repository.UserRepository;
//import com.example.Sorting.security.JwtUtil;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.Map;
//import java.util.Optional;
//
//@RestController
//@RequestMapping("/api/auth")
//@CrossOrigin("*")
//public class AuthController {
//
//    private final UserRepository userRepository;
//    private final JwtUtil jwtUtil;
//
//    public AuthController(UserRepository userRepository, JwtUtil jwtUtil) {
//        this.userRepository = userRepository;
//        this.jwtUtil = jwtUtil;
//    }
//
//    // ✅ Register a New User (JWT)
//    @PostMapping("/register")
//    public ResponseEntity<?> registerUser(@RequestBody User user) {
//        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
//        if (existingUser.isPresent()) {
//            return ResponseEntity.badRequest().body("User already exists!");
//        }
//        userRepository.save(user);
//        return ResponseEntity.ok(Map.of("message", "User registered successfully!"));
//    }
//
//    // ✅ Login with Email/Password (JWT)
//    @PostMapping("/login")
//    public ResponseEntity<?> loginUser(@RequestBody User user) {
//        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
//        if (existingUser.isEmpty() || !existingUser.get().getPassword().equals(user.getPassword())) {
//            return ResponseEntity.badRequest().body("Invalid email or password!");
//        }
//        String token = jwtUtil.generateToken(user.getEmail());
//        return ResponseEntity.ok(Map.of("token", token));
//    }
//
//    // ✅ Google OAuth Login
//    @GetMapping("/google-login")
//    public ResponseEntity<?> googleLogin(OAuth2AuthenticationToken authentication) {
//        String email = authentication.getPrincipal().getAttribute("email");
//
//        // Check if user exists, else create a new user
//        Optional<User> existingUser = userRepository.findByEmail(email);
//        if (existingUser.isEmpty()) {
//            User newUser = new User();
//            newUser.setEmail(email);
//            userRepository.save(newUser);
//        }
//
//        // Generate JWT token for Google user
//        String token = jwtUtil.generateToken(email);
//        return ResponseEntity.ok(Map.of("token", token, "email", email));
//    }
//}