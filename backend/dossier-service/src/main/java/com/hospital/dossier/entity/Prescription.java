package com.hospital.dossier.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "prescriptions")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
/**
 * Entité représentant une ligne de prescription (médicament) dans une consultation.
 */
public class Prescription {
    
    /**
     * Identifiant unique de la prescription.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    /**
     * La consultation associée.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "consultation_id", nullable = false)
    private Consultation consultation;
    
    /**
     * Nom du médicament.
     */
    @Column(nullable = false, length = 200)
    private String medicament;
    
    /**
     * Dosage prescrit.
     */
    @Column(nullable = false, length = 100)
    private String dosage;
    
    /**
     * Fréquence de prise.
     */
    @Column(nullable = false, length = 100)
    private String frequence;
    
    /**
     * Durée du traitement.
     */
    @Column(nullable = false, length = 100)
    private String duree;
    
    /**
     * Instructions particulières.
     */
    @Column(columnDefinition = "TEXT")
    private String instructions;
    
    /**
     * Date de création.
     */
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;
}

