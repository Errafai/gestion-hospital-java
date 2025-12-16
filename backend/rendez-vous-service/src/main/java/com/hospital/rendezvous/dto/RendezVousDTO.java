package com.hospital.rendezvous.dto;

import com.hospital.rendezvous.entity.RendezVous;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Objet de transfert de données représentant un rendez-vous médical.
 * C'est ce format qui est échangé avec le frontend via l'API.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RendezVousDTO {
    
    /**
     * Identifiant technique du rendez-vous (clé primaire).
     */
    private Long id;
    
    /**
     * Identifiant du patient concerné par le rendez-vous.
     * Correspond à l'id d'un patient dans le service patient.
     */
    @NotNull(message = "Patient ID is required")
    private Long patientId;
    
    /**
     * Identifiant du médecin qui reçoit le patient.
     * Correspond à l'id d'un médecin dans ce service.
     */
    @NotNull(message = "Médecin ID is required")
    private Long medecinId;
    
    /**
     * Date du rendez-vous (format ISO : yyyy-MM-dd).
     */
    @NotNull(message = "Date is required")
    private LocalDate dateRdv;
    
    /**
     * Heure de début du rendez-vous (format HH:mm:ss ou HH:mm).
     */
    @NotNull(message = "Heure début is required")
    private LocalTime heureDebut;
    
    /**
     * Heure de fin du rendez-vous.
     */
    @NotNull(message = "Heure fin is required")
    private LocalTime heureFin;
    
    /**
     * Motif principal de la consultation (raison du rendez-vous).
     */
    private String motif;
    
    /**
     * Statut actuel du rendez-vous :
     * PLANIFIE, CONFIRME, ANNULE ou TERMINE.
     */
    private RendezVous.StatutRendezVous statut;
    
    /**
     * Notes complémentaires ajoutées par le médecin ou la réception.
     */
    private String notes;
}

