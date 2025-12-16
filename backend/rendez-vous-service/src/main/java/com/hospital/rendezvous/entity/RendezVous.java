package com.hospital.rendezvous.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Entité JPA représentant un rendez-vous médical entre un patient et un médecin.
 * Stockée dans la table {@code rendez_vous} de la base {@code rendezvous_db}.
 */
@Entity
@Table(name = "rendez_vous")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class RendezVous {
    
    /**
     * Identifiant technique du rendez-vous (clé primaire).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    /**
     * Identifiant du patient qui prend le rendez-vous (provenant du service patient).
     */
    @Column(nullable = false)
    private Long patientId;
    
    /**
     * Identifiant du médecin qui reçoit le patient (provenant de l'entité Medecin).
     */
    @Column(nullable = false)
    private Long medecinId;
    
    /**
     * Date du rendez-vous (sans l'heure).
     */
    @Column(nullable = false)
    private LocalDate dateRdv;
    
    /**
     * Heure de début prévue du rendez-vous.
     */
    @Column(nullable = false)
    private LocalTime heureDebut;
    
    /**
     * Heure de fin prévue du rendez-vous.
     */
    @Column(nullable = false)
    private LocalTime heureFin;
    
    /**
     * Motif ou description du rendez-vous.
     */
    @Column(columnDefinition = "TEXT")
    private String motif;
    
    /**
     * Statut actuel du rendez-vous (PLANIFIE, CONFIRME, ANNULE, TERMINE).
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatutRendezVous statut = StatutRendezVous.PLANIFIE;
    
    /**
     * Notes complémentaires saisies par le médecin ou la réception.
     */
    @Column(columnDefinition = "TEXT")
    private String notes;
    
    /**
     * Date de création du rendez-vous (gérée automatiquement).
     */
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;
    
    /**
     * Date de dernière mise à jour du rendez-vous (gérée automatiquement).
     */
    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime updatedAt;
    
    /**
     * Liste des statuts possibles pour un rendez-vous.
     */
    public enum StatutRendezVous {
        PLANIFIE, CONFIRME, ANNULE, TERMINE
    }
}

