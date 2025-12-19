package com.hospital.patient.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "patients")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
/**
 * Représente un patient dans le système hospitalier.
 * Cette entité est mappée à la table "patients" dans la base de données.
 */
public class Patient {
    
    /**
     * Identifiant unique du patient (Clé Primaire).
     * Généré automatiquement par la base de données.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    /**
     * Numéro unique d'identification du patient (ex: PAT-2023-0001).
     * Doit être unique et non nul.
     */
    @Column(unique = true, nullable = false, length = 20)
    private String numeroPatient;
    
    /**
     * Nom de famille du patient.
     */
    @Column(nullable = false, length = 100)
    private String nom;
    
    /**
     * Prénom du patient.
     */
    @Column(nullable = false, length = 100)
    private String prenom;
    
    /**
     * Date de naissance du patient.
     * Utilisé pour le calcul de l'âge.
     */
    @Column(nullable = false)
    private LocalDate dateNaissance;
    
    /**
     * Sexe du patient (M ou F).
     * Stocké sous forme de chaîne de caractères (String).
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 1)
    private Sexe sexe;
    
    /**
     * Carte d'Identité Nationale (CIN).
     * Doit être unique si renseigné.
     */
    @Column(unique = true, length = 20)
    private String cin;
    
    /**
     * Numéro de téléphone de contact.
     */
    @Column(length = 20)
    private String telephone;
    
    /**
     * Adresse email de contact.
     */
    @Column(length = 100)
    private String email;
    
    /**
     * Adresse postale complète.
     */
    @Column(columnDefinition = "TEXT")
    private String adresse;
    
    /**
     * Ville de résidence.
     */
    @Column(length = 100)
    private String ville;
    
    /**
     * Groupe sanguin (ex: A+, O-, etc.).
     */
    @Column(length = 5)
    private String groupeSanguin;
    
    /**
     * Liste des allergies connues (texte libre).
     */
    @Column(columnDefinition = "TEXT")
    private String allergies;
    
    /**
     * Date de création de l'enregistrement.
     * Géré automatiquement par JPA Auditing.
     */
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;
    
    /**
     * Date de la dernière modification de l'enregistrement.
     * Géré automatiquement par JPA Auditing.
     */
    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime updatedAt;
    
    /**
     * Énumération pour le sexe du patient.
     */
    public enum Sexe {
        M, F
    }
}

