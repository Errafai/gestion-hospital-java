package com.hospital.dossier.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Objet de transfert de données pour représenter un dossier médical
 * côté API sans exposer directement l'entité JPA.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DossierMedicalDTO {
    
    /**
     * Identifiant technique du dossier médical (clé primaire).
     */
    private Long id;
    
    /**
     * Identifiant du patient auquel appartient ce dossier médical.
     * Correspond à l'id d'un patient dans le service patient.
     */
    @NotNull(message = "Patient ID is required")
    private Long patientId;
    
    /**
     * Numéro de dossier médical (référence métier visible côté utilisateur).
     * Peut être fourni par le frontend ou généré automatiquement côté backend.
     */
    private String numeroDossier;
    
    /**
     * Antécédents médicaux du patient (maladies chroniques, pathologies…).
     */
    private String antecedentsMedicaux;
    
    /**
     * Antécédents chirurgicaux du patient (opérations passées).
     */
    private String antecedentsChirurgicaux;
    
    /**
     * Antécédents familiaux (maladies héréditaires, antécédents familiaux).
     */
    private String antecedentsFamiliaux;
}

