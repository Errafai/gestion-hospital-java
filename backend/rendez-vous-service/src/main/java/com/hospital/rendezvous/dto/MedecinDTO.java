package com.hospital.rendezvous.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Objet de transfert de données représentant un médecin côté API.
 * Utilisé pour échanger des informations entre le frontend et le service rendez-vous.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedecinDTO {
    
    /**
     * Identifiant technique du médecin (clé primaire côté base de données).
     */
    private Long id;
    
    /**
     * Identifiant de l'utilisateur associé (dans le service d'authentification).
     * Permet de lier le médecin à un compte User (login / mot de passe / rôle MEDECIN).
     */
    @NotNull(message = "User ID is required")
    private Long userId;
    
    /**
     * Numéro d'inscription à l'ordre des médecins.
     * Doit être unique pour chaque médecin.
     */
    @NotBlank(message = "Numéro ordre is required")
    private String numeroOrdre;
    
    /**
     * Spécialité médicale du médecin (Cardiologie, Pédiatrie, etc.).
     */
    @NotBlank(message = "Spécialité is required")
    private String specialite;
    
    /**
     * Numéro de téléphone professionnel du médecin.
     */
    private String telephone;

    /**
     * Indique si le médecin accepte actuellement de nouveaux rendez-vous
     * (true = disponible, false = indisponible).
     */
    private Boolean disponible;
}

