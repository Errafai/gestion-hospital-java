package com.hospital.auth.controller;

import com.hospital.auth.dto.JwtAuthenticationResponse;
import com.hospital.auth.dto.LoginRequest;
import com.hospital.auth.dto.RegisterRequest;
import com.hospital.auth.entity.User;
import com.hospital.auth.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
/**
 * Contrôleur REST pour la gestion de l'authentification.
 * Expose les endpoints publics pour l'inscription et la connexion.
 */
public class AuthController {
    
    @Autowired
    private AuthService authService;
    
    /**
     * Endpoint d'inscription d'un nouvel utilisateur.
     * Accessible publiquement.
     * @param request Données de l'utilisateur.
     * @return L'utilisateur créé avec le statut 201 Created.
     */
    @PostMapping("/register")
    public ResponseEntity<User> register(@Valid @RequestBody RegisterRequest request) {
        User user = authService.register(request);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
    
    /**
     * Endpoint de connexion.
     * Authentifie l'utilisateur et retourne un token JWT.
     * @param request Identifiants de connexion.
     * @return Le token JWT et les rôles.
     */
    @PostMapping("/login")
    public ResponseEntity<JwtAuthenticationResponse> login(@Valid @RequestBody LoginRequest request) {
        JwtAuthenticationResponse response = authService.login(request);
        return ResponseEntity.ok(response);
    }
    
    /**
     * Endpoint pour rafraîchir le token (Non implémenté).
     */
    @PostMapping("/refresh")
    public ResponseEntity<String> refresh() {
        return ResponseEntity.ok("Refresh token endpoint");
    }
    
    /**
     * Endpoint pour récupérer les infos de l'utilisateur courant (Exemple).
     */
    @GetMapping("/me")
    public ResponseEntity<String> getCurrentUser() {
        return ResponseEntity.ok("Current user endpoint");
    }
    
    /**
     * Endpoint de déconnexion (Côté client principalement, car JWT est stateless).
     */
    @PostMapping("/logout")
    public ResponseEntity<String> logout() {
        return ResponseEntity.ok("Logout successful");
    }
}

