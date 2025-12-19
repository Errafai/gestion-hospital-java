package com.hospital.dossier.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "dossiers_medicaux")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
/**
 * Entité représentant le dossier médical d'un patient.
 * Centralise l'historique médical, les consultations et les documents.
 */
public class DossierMedical {
    
    /**
     * Identifiant unique du dossier.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    /**
     * ID du patient associé.
     */
    @Column(nullable = false)
    private Long patientId;
    
    /**
     * Numéro de dossier unique.
     */
    @Column(unique = true, nullable = false, length = 50)
    private String numeroDossier;
    
    /**
     * Date d'ouverture du dossier.
     */
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime dateCreation;
    
    /**
     * Antécédents médicaux (maladies chroniques, etc.).
     */
    @Column(columnDefinition = "TEXT")
    private String antecedentsMedicaux;
    
    /**
     * Antécédents chirurgicaux (opérations passées).
     */
    @Column(columnDefinition = "TEXT")
    private String antecedentsChirurgicaux;
    
    /**
     * Antécédents familiaux (hérédité).
     */
    @Column(columnDefinition = "TEXT")
    private String antecedentsFamiliaux;
    
    /**
     * Date de dernière mise à jour.
     */
    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime updatedAt;
    
    /**
     * Liste des consultations associées au dossier.
     */
    @OneToMany(mappedBy = "dossierMedical", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Consultation> consultations = new ArrayList<>();
    
    /**
     * Liste des documents (PDF, Imagerie) associés.
     */
    @OneToMany(mappedBy = "dossierMedical", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Document> documents = new ArrayList<>();
}

