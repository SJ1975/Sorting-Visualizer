//package com.example.Sorting.security;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.config.http.SessionCreationPolicy;
//
//@Configuration
//public class SecurityConfig {
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .cors(cors -> cors.disable()) // ✅ Disable built-in CORS (we handle it in CorsConfig)
//                .csrf(csrf -> csrf.disable()) // ✅ Disable CSRF for API access
//                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // ✅ No sessions
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/api/auth/**").permitAll() // ✅ Allow authentication endpoints
//                        .anyRequest().authenticated() // ✅ Protect other routes
//                );
//
//        return http.build();
//    }
//}