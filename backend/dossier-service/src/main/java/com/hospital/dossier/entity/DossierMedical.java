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

/**
 * Entité JPA représentant le dossier médical d'un patient.
 * Chaque patient possède au maximum un dossier médical.
 */
@Entity
@Table(name = "dossiers_medicaux")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class DossierMedical {
    
    /**
     * Identifiant technique du dossier médical (clé primaire).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    /**
     * Identifiant du patient auquel appartient ce dossier.
     */
    @Column(nullable = false)
    private Long patientId;
    
    /**
     * Numéro de dossier unique (référence médicale utilisée par le personnel).
     */
    @Column(unique = true, nullable = false, length = 50)
    private String numeroDossier;
    
    /**
     * Date de création du dossier médical (gérée automatiquement).
     */
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime dateCreation;
    
    /**
     * Antécédents médicaux du patient (maladies, pathologies chroniques…).
     */
    @Column(columnDefinition = "TEXT")
    private String antecedentsMedicaux;
    
    /**
     * Antécédents chirurgicaux du patient (opérations passées).
     */
    @Column(columnDefinition = "TEXT")
    private String antecedentsChirurgicaux;
    
    /**
     * Antécédents familiaux (maladies présentes dans la famille).
     */
    @Column(columnDefinition = "TEXT")
    private String antecedentsFamiliaux;
    
    /**
     * Date de dernière mise à jour du dossier (gérée automatiquement).
     */
    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime updatedAt;
    
    /**
     * Liste des consultations médicales associées à ce dossier.
     */
    @OneToMany(mappedBy = "dossierMedical", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Consultation> consultations = new ArrayList<>();
    
    /**
     * Liste des documents médicaux associés (analyses, comptes-rendus…).
     */
    @OneToMany(mappedBy = "dossierMedical", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Document> documents = new ArrayList<>();
}

