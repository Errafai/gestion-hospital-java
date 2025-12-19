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

@Entity
@Table(name = "consultations")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
/**
 * Entité représentant une consultation médicale.
 * Enregistre le diagnostic, le traitement et les prescriptions.
 */
public class Consultation {
    
    /**
     * Identifiant unique de la consultation.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    /**
     * Le dossier médical auquel cette consultation est rattachée.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dossier_medical_id", nullable = false)
    private DossierMedical dossierMedical;
    
    /**
     * ID du médecin ayant réalisé la consultation.
     */
    @Column(nullable = false)
    private Long medecinId;
    
    /**
     * ID du rendez-vous associé (optionnel).
     */
    private Long rendezVousId;
    
    /**
     * Date de la consultation.
     */
    @Column(nullable = false)
    private LocalDateTime dateConsultation;
    
    /**
     * Symptômes décrits par le patient.
     */
    @Column(columnDefinition = "TEXT")
    private String symptomes;
    
    /**
     * Diagnostic établi par le médecin.
     */
    @Column(columnDefinition = "TEXT")
    private String diagnostic;
    
    /**
     * Traitement prescrit.
     */
    @Column(columnDefinition = "TEXT")
    private String traitement;
    
    /**
     * Observations supplémentaires.
     */
    @Column(columnDefinition = "TEXT")
    private String observations;
    
    /**
     * Date de création de l'enregistrement.
     */
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;
    
    /**
     * Liste des prescriptions médicamenteuses associées.
     */
    @OneToMany(mappedBy = "consultation", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Prescription> prescriptions = new ArrayList<>();
}

