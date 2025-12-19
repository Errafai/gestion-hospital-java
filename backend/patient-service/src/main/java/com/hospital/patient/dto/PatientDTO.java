package com.hospital.patient.dto;

import com.hospital.patient.entity.Patient;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
/**
 * Objet de Transfert de Données (DTO) pour l'entité Patient.
 * Utilisé pour exposer les données du patient via l'API REST.
 * Contient des annotations de validation pour assurer l'intégrité des données reçues.
 */
public class PatientDTO {
    
    /**
     * Identifiant technique du patient.
     */
    private Long id;
    
    /**
     * Numéro unique du patient. Obligatoire.
     */
    @NotBlank(message = "Numéro patient is required")
    private String numeroPatient;
    
    /**
     * Nom du patient. Obligatoire.
     */
    @NotBlank(message = "Nom is required")
    private String nom;
    
    /**
     * Prénom du patient. Obligatoire.
     */
    @NotBlank(message = "Prénom is required")
    private String prenom;
    
    /**
     * Date de naissance. Obligatoire.
     */
    @NotNull(message = "Date de naissance is required")
    private LocalDate dateNaissance;
    
    /**
     * Sexe du patient (M/F). Obligatoire.
     */
    @NotNull(message = "Sexe is required")
    private Patient.Sexe sexe;
    
    /**
     * Carte d'identité nationale.
     */
    private String cin;
    
    /**
     * Numéro de téléphone.
     */
    private String telephone;
    
    /**
     * Adresse Email.
     */
    private String email;
    
    /**
     * Adresse physique.
     */
    private String adresse;
    
    /**
     * Ville de résidence.
     */
    private String ville;
    
    /**
     * Groupe sanguin.
     */
    private String groupeSanguin;
    
    /**
     * Allergies connues.
     */
    private String allergies;
}

