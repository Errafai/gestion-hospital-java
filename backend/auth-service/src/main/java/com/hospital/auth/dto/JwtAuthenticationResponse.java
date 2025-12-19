package com.hospital.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO pour la réponse d'authentification.
 * Renvoyé après une connexion réussie.
 */
public class JwtAuthenticationResponse {
    /**
     * Le token JWT généré.
     */
    private String token;
    
    /**
     * Le type de token (par défaut "Bearer").
     */
    private String type = "Bearer";
    
    /**
     * Le nom d'utilisateur authentifié.
     */
    private String username;
    
    /**
     * Le rôle de l'utilisateur.
     */
    private String role;

    public JwtAuthenticationResponse() {
    }

    public JwtAuthenticationResponse(String token, String type, String username, String role) {
        this.token = token;
        this.type = type;
        this.username = username;
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}

