package com.hospital.rendezvous.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO (Data Transfer Object) pour l'entité Médecin.
 * Utilisé pour exposer les données des médecins via l'API.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedecinDTO {
    
    /**
     * Identifiant du médecin (optionnel à la création).
     */
    private Long id;
    
    /**
     * ID de l'utilisateur associé. Obligatoire.
     */
    @NotNull(message = "User ID is required")
    private Long userId;
    
    /**
     * Numéro d'ordre. Unique et obligatoire.
     */
    @NotBlank(message = "Numéro ordre is required")
    private String numeroOrdre;
    
    /**
     * Spécialité médicale. Obligatoire.
     */
    @NotBlank(message = "Spécialité is required")
    private String specialite;
    
    /**
     * Numéro de téléphone.
     */
    private String telephone;
    
    /**
     * Disponibilité du médecin.
     */
    private Boolean disponible;
}

