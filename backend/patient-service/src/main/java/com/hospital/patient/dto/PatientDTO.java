package com.hospital.patient.dto;

import com.hospital.patient.entity.Patient;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Objet de transfert de données utilisé entre le backend et le frontend
 * pour représenter un patient sans exposer directement l'entité JPA.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientDTO {
    
    /**
     * Identifiant technique du patient (généré par la base).
     */
    private Long id;
    
    /**
     * Numéro unique attribué au patient par l'hôpital
     * (utilisé comme identifiant métier).
     */
    @NotBlank(message = "Numéro patient is required")
    private String numeroPatient;
    
    /**
     * Nom de famille du patient.
     */
    @NotBlank(message = "Nom is required")
    private String nom;
    
    /**
     * Prénom du patient.
     */
    @NotBlank(message = "Prénom is required")
    private String prenom;
    
    /**
     * Date de naissance du patient (format ISO : yyyy-MM-dd).
     */
    @NotNull(message = "Date de naissance is required")
    private LocalDate dateNaissance;
    
    /**
     * Sexe du patient (M ou F) défini par l'enum {@link Patient.Sexe}.
     */
    @NotNull(message = "Sexe is required")
    private Patient.Sexe sexe;
    
    /**
     * Numéro de CIN (facultatif) du patient.
     * Doit être unique si renseigné.
     */
    private String cin;
    
    /**
     * Numéro de téléphone du patient.
     */
    private String telephone;
    
    /**
     * Adresse email du patient.
     */
    private String email;
    
    /**
     * Adresse postale complète du patient.
     */
    private String adresse;
    
    /**
     * Ville de résidence du patient.
     */
    private String ville;
    
    /**
     * Groupe sanguin du patient (ex : A+, O-, etc.).
     */
    private String groupeSanguin;
    
    /**
     * Informations sur les allergies connues du patient.
     */
    private String allergies;
}

