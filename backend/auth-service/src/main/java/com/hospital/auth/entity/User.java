package com.hospital.auth.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

/**
 * Entité représentant un utilisateur de la plateforme (compte de connexion).
 * Stockée dans la base de données {@code auth_db} dans la table {@code users}.
 * Cette entité est utilisée par le service d'authentification pour gérer :
 * - la connexion (username / password)
 * - les rôles (ADMIN, MEDECIN, RECEPTIONNISTE)
 * - l'état du compte (actif ou non)
 */
@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class User {
    
    /**
     * Identifiant technique unique de l'utilisateur (clé primaire).
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
     * Mot de passe chiffré (BCrypt) stocké en base.
     * Le mot de passe en clair ne doit jamais être enregistré.
     */
    @Column(nullable = false)
    private String password;
    
    /**
     * Email unique de l'utilisateur.
     */
    @Column(unique = true, nullable = false, length = 100)
    private String email;
    
    /**
     * Nom de famille de l'utilisateur.
     */
    @Column(nullable = false, length = 100)
    private String nom;
    
    /**
     * Prénom de l'utilisateur.
     */
    @Column(nullable = false, length = 100)
    private String prenom;
    
    /**
     * Rôle de l'utilisateur dans le système.
     * Utilisé par Spring Security pour contrôler l'accès aux ressources.
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;
    
    /**
     * Indique si le compte est actif (true) ou désactivé/bloqué (false).
     */
    @Column(nullable = false)
    private Boolean actif = true;
    
    /**
     * Date de création du compte.
     * Remplie automatiquement grâce à l'auditing JPA.
     */
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;
    
    /**
     * Date de dernière mise à jour des informations du compte.
     * Remplie automatiquement grâce à l'auditing JPA.
     */
    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime updatedAt;
    
    /**
     * Enumération des rôles possibles pour un utilisateur.
     * - ADMIN : accès complet à la plateforme
     * - MEDECIN : accès aux fonctionnalités médicales (rendez-vous, consultations, dossiers)
     * - RECEPTIONNISTE : gestion des patients et des rendez-vous uniquement
     */
    public enum Role {
        ADMIN, MEDECIN, RECEPTIONNISTE
    }
}

