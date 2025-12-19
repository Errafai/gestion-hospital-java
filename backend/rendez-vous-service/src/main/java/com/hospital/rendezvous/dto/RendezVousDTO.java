package com.hospital.rendezvous.dto;

import com.hospital.rendezvous.entity.RendezVous;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * DTO pour l'entité RendezVous.
 * Utilisé pour la création et l'affichage des rendez-vous.
 */
public class RendezVousDTO {
    
    /**
     * ID du rendez-vous (optionnel à la création).
     */
    private Long id;
    
    /**
     * ID du patient concerné. Obligatoire.
     */
    @NotNull(message = "Patient ID is required")
    private Long patientId;
    
    /**
     * ID du médecin concerné. Obligatoire.
     */
    @NotNull(message = "Médecin ID is required")
    private Long medecinId;
    
    /**
     * Date du rendez-vous. Obligatoire.
     */
    @NotNull(message = "Date is required")
    private LocalDate dateRdv;
    
    /**
     * Heure de début. Obligatoire.
     */
    @NotNull(message = "Heure début is required")
    private LocalTime heureDebut;
    
    /**
     * Heure de fin. Obligatoire.
     */
    @NotNull(message = "Heure fin is required")
    private LocalTime heureFin;
    
    /**
     * Motif de la consultation.
     */
    private String motif;
    
    /**
     * Statut du rendez-vous.
     */
    private RendezVous.StatutRendezVous statut;
    
    /**
     * Notes complémentaires.
     */
    private String notes;

    public RendezVousDTO() {
    }

    public RendezVousDTO(Long id, Long patientId, Long medecinId, LocalDate dateRdv, LocalTime heureDebut, LocalTime heureFin, String motif, RendezVous.StatutRendezVous statut, String notes) {
        this.id = id;
        this.patientId = patientId;
        this.medecinId = medecinId;
        this.dateRdv = dateRdv;
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
        this.motif = motif;
        this.statut = statut;
        this.notes = notes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public Long getMedecinId() {
        return medecinId;
    }

    public void setMedecinId(Long medecinId) {
        this.medecinId = medecinId;
    }

    public LocalDate getDateRdv() {
        return dateRdv;
    }

    public void setDateRdv(LocalDate dateRdv) {
        this.dateRdv = dateRdv;
    }

    public LocalTime getHeureDebut() {
        return heureDebut;
    }

    public void setHeureDebut(LocalTime heureDebut) {
        this.heureDebut = heureDebut;
    }

    public LocalTime getHeureFin() {
        return heureFin;
    }

    public void setHeureFin(LocalTime heureFin) {
        this.heureFin = heureFin;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public RendezVous.StatutRendezVous getStatut() {
        return statut;
    }

    public void setStatut(RendezVous.StatutRendezVous statut) {
        this.statut = statut;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}

