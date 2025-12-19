package com.hospital.auth.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)
/**
 * Représente un utilisateur du système (Administrateur, Médecin, Réceptionniste, etc.).
 * Cette entité gère l'authentification et les droits d'accès.
 */
public class User {
    
    /**
     * Identifiant unique de l'utilisateur.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    /**
     * Nom d'utilisateur unique utilisé pour la connexion.
     */
    @Column(unique = true, nullable = false, length = 50)
    private String username;
    
    /**
     * Mot de passe hashé de l'utilisateur.
     * Ne jamais stocker en clair.
     */
    @Column(nullable = false)
    private String password;
    
    /**
     * Adresse email unique de l'utilisateur.
     */
    @Column(unique = true, nullable = false, length = 100)
    private String email;
    
    /**
     * Nom de famille.
     */
    @Column(nullable = false, length = 100)
    private String nom;
    
    /**
     * Prénom.
     */
    @Column(nullable = false, length = 100)
    private String prenom;
    
    /**
     * Rôle de l'utilisateur définissant ses permissions.
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;
    
    /**
     * Indique si le compte est actif ou désactivé.
     */
    @Column(nullable = false)
    private Boolean actif = true;
    
    /**
     * Date de création du compte.
     */
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;
    
    /**
     * Date de dernière modification du compte.
     */
    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime updatedAt;
    
    /**
     * Rôles disponibles dans l'application.
     */
    public enum Role {
        ADMIN, MEDECIN, RECEPTIONNISTE
    }

    public User() {
    }

    public User(Long id, String username, String password, String email, String nom, String prenom, Role role, Boolean actif, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.nom = nom;
        this.prenom = prenom;
        this.role = role;
        this.actif = actif;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Boolean getActif() {
        return actif;
    }

    public void setActif(Boolean actif) {
        this.actif = actif;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}

