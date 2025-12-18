package com.hospital.dossier.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Entité JPA représentant une consultation médicale liée à un dossier.
 */
@Entity
@Table(name = "consultations")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Consultation {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    /**
     * Dossier médical auquel cette consultation est rattachée.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dossier_medical_id", nullable = false)
    private DossierMedical dossierMedical;
    
    /**
     * Identifiant du médecin qui a réalisé la consultation.
     */
    @Column(nullable = false)
    private Long medecinId;
    
    /**
     * Identifiant du rendez-vous lié à cette consultation (optionnel).
     */
    private Long rendezVousId;
    
    /**
     * Date et heure de réalisation de la consultation.
     */
    @Column(nullable = false)
    private LocalDateTime dateConsultation;
    
    /**
     * Symptômes décrits par le patient.
     */
    @Column(columnDefinition = "TEXT")
    private String symptomes;
    
    /**
     * Diagnostic posé par le médecin.
     */
    @Column(columnDefinition = "TEXT")
    private String diagnostic;
    
    /**
     * Traitement prescrit au patient (médicaments, recommandations…).
     */
    @Column(columnDefinition = "TEXT")
    private String traitement;
    
    /**
     * Observations et remarques complémentaires du médecin.
     */
    @Column(columnDefinition = "TEXT")
    private String observations;
    
    /**
     * Date de création de l'enregistrement de la consultation.
     */
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;
    
    /**
     * Liste des prescriptions (ordonnances) associées à cette consultation.
     */
    @OneToMany(mappedBy = "consultation", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Prescription> prescriptions = new ArrayList<>();
}

