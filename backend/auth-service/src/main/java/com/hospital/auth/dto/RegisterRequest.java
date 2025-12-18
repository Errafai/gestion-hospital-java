package com.hospital.auth.dto;

import com.hospital.auth.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Représente la charge utile envoyée par le frontend lors de l'inscription d'un nouvel utilisateur.
 * Cette classe ne contient que les données nécessaires pour créer un {@link User}.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    
    /**
     * Nom d'utilisateur unique choisi par l'utilisateur (utilisé pour se connecter).
     */
    @NotBlank(message = "Username is required")
    private String username;
    
    /**
     * Mot de passe en clair envoyé par le frontend.
     * Il sera chiffré côté backend avant d'être stocké dans la base.
     */
    @NotBlank(message = "Password is required")
    private String password;
    
    /**
     * Adresse email de l'utilisateur (également unique).
     * Utilisée pour l'identification et la communication.
     */
    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;
    
    /**
     * Nom de famille de l'utilisateur (affichage, informations personnelles).
     */
    @NotBlank(message = "Nom is required")
    private String nom;
    
    /**
     * Prénom de l'utilisateur.
     */
    @NotBlank(message = "Prénom is required")
    private String prenom;
    
    /**
     * Rôle fonctionnel de l'utilisateur dans le système :
     * ADMIN, MEDECIN ou RECEPTIONNISTE.
     * Permet de gérer les autorisations côté frontend et backend.
     */
    @NotNull(message = "Role is required")
    private User.Role role;
}

