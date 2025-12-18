package com.hospital.auth.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Requête d'authentification envoyée par le frontend lors du login.
 * Contient les informations nécessaires pour vérifier l'identité d'un utilisateur.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {
    
    /**
     * Nom d'utilisateur de la personne qui se connecte.
     */
    @NotBlank(message = "Username is required")
    private String username;
    
    /**
     * Mot de passe en clair saisi par l'utilisateur.
     * Il sera comparé à la version chiffrée stockée en base.
     */
    @NotBlank(message = "Password is required")
    private String password;
}

