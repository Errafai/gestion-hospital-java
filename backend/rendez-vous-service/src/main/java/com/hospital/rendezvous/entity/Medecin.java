package com.hospital.rendezvous.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "medecins")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
/**
 * Entité représentant un médecin dans le système.
 * Lié à un utilisateur (User) pour l'authentification.
 */
public class Medecin {
    
    /**
     * Identifiant unique du médecin.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    /**
     * ID de l'utilisateur associé (Compte d'authentification).
     */
    @Column(nullable = false)
    private Long userId;
    
    /**
     * Numéro d'ordre unique du médecin.
     */
    @Column(unique = true, nullable = false, length = 50)
    private String numeroOrdre;
    
    /**
     * Spécialité médicale (ex: Cardiologue, Généraliste).
     */
    @Column(nullable = false, length = 100)
    private String specialite;
    
    /**
     * Numéro de téléphone professionnel.
     */
    @Column(length = 20)
    private String telephone;
    
    /**
     * Indique si le médecin est disponible pour des consultations.
     */
    @Column(nullable = false)
    private Boolean disponible = true;
    
    /**
     * Date de création du profil médecin.
     */
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;
}

