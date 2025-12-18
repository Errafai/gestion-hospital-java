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

/**
 * Entité JPA représentant un patient dans la base de données {@code patient_db}.
 * Cette classe mappe la table {@code patients}.
 */
@Entity
@Table(name = "patients")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Patient {
    
    /**
        * Identifiant technique du patient (clé primaire).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    /**
     * Numéro de dossier patient unique dans l'hôpital
     * (exemple : PAT-001, PAT-2024-0001).
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
     */
    @Column(nullable = false)
    private LocalDate dateNaissance;
    
    /**
     * Sexe du patient (M ou F), stocké comme chaîne.
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 1)
    private Sexe sexe;
    
    /**
     * Numéro de carte d'identité nationale (optionnel) du patient.
     * Doit être unique si renseigné.
     */
    @Column(unique = true, length = 20)
    private String cin;
    
    /**
     * Numéro de téléphone du patient.
     */
    @Column(length = 20)
    private String telephone;
    
    /**
     * Adresse email du patient.
     */
    @Column(length = 100)
    private String email;
    
    /**
     * Adresse postale détaillée du patient.
     */
    @Column(columnDefinition = "TEXT")
    private String adresse;
    
    /**
     * Ville principale de résidence du patient.
     */
    @Column(length = 100)
    private String ville;
    
    /**
     * Groupe sanguin du patient (A+, A-, B+, B-, AB+, AB-, O+, O-).
     */
    @Column(length = 5)
    private String groupeSanguin;
    
    /**
     * Liste ou description des allergies connues du patient.
     */
    @Column(columnDefinition = "TEXT")
    private String allergies;
    
    /**
     * Date de création de l'enregistrement du patient.
     * Gérée automatiquement par JPA (auditing).
     */
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;
    
    /**
     * Date de dernière mise à jour des informations du patient.
     * Gérée automatiquement par JPA (auditing).
     */
    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime updatedAt;
    
    /**
     * Enumération des valeurs possibles pour le sexe du patient.
     * M : Masculin, F : Féminin.
     */
    public enum Sexe {
        M, F
    }
}

