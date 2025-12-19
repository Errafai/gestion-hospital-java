package com.hospital.auth.dto;

import com.hospital.auth.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO pour la requête d'inscription.
 * Contient les informations nécessaires pour créer un nouvel utilisateur.
 */
public class RegisterRequest {
    
    /**
     * Nom d'utilisateur souhaité. Obligatoire.
     */
    @NotBlank(message = "Username is required")
    private String username;
    
    /**
     * Mot de passe. Obligatoire.
     */
    @NotBlank(message = "Password is required")
    private String password;
    
    /**
     * Adresse email valide. Obligatoire.
     */
    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;
    
    /**
     * Nom de famille. Obligatoire.
     */
    @NotBlank(message = "Nom is required")
    private String nom;
    
    /**
     * Prénom. Obligatoire.
     */
    @NotBlank(message = "Prénom is required")
    private String prenom;
    
    /**
     * Rôle de l'utilisateur (ADMIN, MEDECIN, RECEPTIONNISTE). Obligatoire.
     */
    @NotNull(message = "Role is required")
    private User.Role role;

    public RegisterRequest() {
    }

    public RegisterRequest(String username, String password, String email, String nom, String prenom, User.Role role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.nom = nom;
        this.prenom = prenom;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public User.Role getRole() {
        return role;
    }

    public void setRole(User.Role role) {
        this.role = role;
    }
}

