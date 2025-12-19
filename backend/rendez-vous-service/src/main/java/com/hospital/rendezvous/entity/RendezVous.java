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

@Entity
@Table(name = "rendez_vous")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
/**
 * Entité représentant un rendez-vous médical.
 * Lie un patient à un médecin pour une date et un créneau horaire donnés.
 */
public class RendezVous {
    
    /**
     * Identifiant unique du rendez-vous.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    /**
     * Identifiant du patient concerné.
     */
    @Column(nullable = false)
    private Long patientId;
    
    /**
     * Identifiant du médecin concerné.
     */
    @Column(nullable = false)
    private Long medecinId;
    
    /**
     * Date du rendez-vous.
     */
    @Column(nullable = false)
    private LocalDate dateRdv;
    
    /**
     * Heure de début du rendez-vous.
     */
    @Column(nullable = false)
    private LocalTime heureDebut;
    
    /**
     * Heure de fin prévisionnelle.
     */
    @Column(nullable = false)
    private LocalTime heureFin;
    
    /**
     * Motif de la consultation.
     */
    @Column(columnDefinition = "TEXT")
    private String motif;
    
    /**
     * Statut actuel du rendez-vous.
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatutRendezVous statut = StatutRendezVous.PLANIFIE;
    
    /**
     * Notes additionnelles du médecin.
     */
    @Column(columnDefinition = "TEXT")
    private String notes;
    
    /**
     * Date de création de l'enregistrement.
     */
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;
    
    /**
     * Date de dernière modification.
     */
    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime updatedAt;
    
    /**
     * Énumération des statuts possibles d'un rendez-vous.
     */
    public enum StatutRendezVous {
        PLANIFIE, CONFIRME, ANNULE, TERMINE
    }
}

