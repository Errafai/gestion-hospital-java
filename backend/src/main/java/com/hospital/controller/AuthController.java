package com.hospital.controller;

import com.hospital.dto.JwtAuthenticationResponse;
import com.hospital.dto.LoginRequest;
import com.hospital.dto.RegisterRequest;
import com.hospital.entity.User;
import com.hospital.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {
    
    @Autowired
    private AuthService authService;
    
    @PostMapping("/register")
    public ResponseEntity<User> register(@Valid @RequestBody RegisterRequest request) {
        User user = authService.register(request);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
    
    @PostMapping("/login")
    public ResponseEntity<JwtAuthenticationResponse> login(@Valid @RequestBody LoginRequest request) {
        JwtAuthenticationResponse response = authService.login(request);
        return ResponseEntity.ok(response);
    }
    
    @PostMapping("/refresh")
    public ResponseEntity<String> refresh() {
        // TODO: Implement refresh token logic
        return ResponseEntity.ok("Refresh token endpoint");
    }
    
    @GetMapping("/me")
    public ResponseEntity<String> getCurrentUser() {
        // TODO: Implement get current user logic
        return ResponseEntity.ok("Current user endpoint");
    }
    
    @PostMapping("/logout")
    public ResponseEntity<String> logout() {
        // TODO: Implement logout logic (token blacklist)
        return ResponseEntity.ok("Logout successful");
    }
}

