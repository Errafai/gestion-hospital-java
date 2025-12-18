package com.hospital.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Réponse renvoyée au frontend après une authentification réussie.
 * Contient le token JWT et quelques informations de base sur l'utilisateur connecté.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtAuthenticationResponse {
    /**
     * Token JWT signé que le frontend doit renvoyer dans l'en-tête
     * Authorization: Bearer &lt;token&gt; pour chaque requête protégée.
     */
    private String token;
    
    /**
     * Type du token renvoyé. Ici toujours "Bearer".
     * Permet au frontend de construire facilement l'en-tête Authorization.
     */
    private String type = "Bearer";
    
    /**
     * Nom d'utilisateur connecté (utile pour l'affichage côté UI).
     */
    private String username;
    
    /**
     * Rôle de l'utilisateur connecté (ADMIN, MEDECIN, RECEPTIONNISTE).
     * Permet au frontend d'afficher ou cacher certaines fonctionnalités.
     */
    private String role;
}

