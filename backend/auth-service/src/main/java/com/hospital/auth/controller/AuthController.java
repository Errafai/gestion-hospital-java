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

/**
 * Contrôleur REST du service d'authentification.
 * Expose les endpoints utilisés par le frontend pour s'enregistrer et se connecter.
 */
@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {
    
    @Autowired
    private AuthService authService;
    
    /**
     * Endpoint d'inscription d'un nouvel utilisateur.
     * Le frontend envoie les informations dans {@link RegisterRequest}.
     */
    @PostMapping("/register")
    public ResponseEntity<User> register(@Valid @RequestBody RegisterRequest request) {
        User user = authService.register(request);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
    
    /**
     * Endpoint de connexion.
     * - Entrée : {@link LoginRequest} (username + password)
     * - Sortie : {@link JwtAuthenticationResponse} contenant le token JWT.
     */
    @PostMapping("/login")
    public ResponseEntity<JwtAuthenticationResponse> login(@Valid @RequestBody LoginRequest request) {
        JwtAuthenticationResponse response = authService.login(request);
        return ResponseEntity.ok(response);
    }
    
    /**
     * (Placeholder) Endpoint prévu pour rafraîchir un token JWT.
     * À implémenter si besoin dans une version ultérieure.
     */
    @PostMapping("/refresh")
    public ResponseEntity<String> refresh() {
        return ResponseEntity.ok("Refresh token endpoint");
    }
    
    /**
     * (Placeholder) Endpoint pour récupérer les informations de l'utilisateur connecté.
     * À compléter pour renvoyer un vrai objet User/Profil.
     */
    @GetMapping("/me")
    public ResponseEntity<String> getCurrentUser() {
        return ResponseEntity.ok("Current user endpoint");
    }
    
    /**
     * (Placeholder) Endpoint de déconnexion.
     * Actuellement purement informatif (aucune invalidation de token côté serveur).
     */
    @PostMapping("/logout")
    public ResponseEntity<String> logout() {
        return ResponseEntity.ok("Logout successful");
    }
}

