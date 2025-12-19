package com.hospital.dossier.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "documents")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
/**
 * Entité représentant un document joint au dossier médical (Laboratoire, Radio, etc.).
 */
public class Document {
    
    /**
     * Identifiant unique du document.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    /**
     * Le dossier médical auquel ce document appartient.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dossier_medical_id", nullable = false)
    private DossierMedical dossierMedical;
    
    /**
     * Type de document (ex: ANALYSE_SANG, RADIOGRAPHIE).
     */
    @Column(nullable = false, length = 50)
    private String typeDocument;
    
    /**
     * Nom original du fichier.
     */
    @Column(nullable = false, length = 255)
    private String nomFichier;
    
    /**
     * Chemin de stockage du fichier.
     */
    @Column(nullable = false, length = 500)
    private String cheminFichier;
    
    /**
     * Taille du fichier en octets.
     */
    @Column(nullable = false)
    private Long taille;
    
    /**
     * Date d'ajout du document.
     */
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime uploadedAt;
}

