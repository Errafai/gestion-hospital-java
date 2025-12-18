package com.hospital.rendezvous.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

/**
 * Entité JPA représentant un médecin dans la base {@code rendezvous_db}.
 * Elle est reliée à un utilisateur (service d'authentification) via {@code userId}.
 */
@Entity
@Table(name = "medecins")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Medecin {
    
    /**
     * Identifiant technique du médecin (clé primaire).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    /**
     * Identifiant de l'utilisateur (entité User dans {@code auth_db}).
     * Permet de lier les informations métier du médecin à son compte de connexion.
     */
    @Column(nullable = false)
    private Long userId;
    
    /**
     * Numéro d'inscription à l'ordre des médecins (unique).
     */
    @Column(unique = true, nullable = false, length = 50)
    private String numeroOrdre;
    
    /**
     * Spécialité médicale principale du médecin (ex : Cardiologie).
     */
    @Column(nullable = false, length = 100)
    private String specialite;
    
    /**
     * Numéro de téléphone professionnel du médecin.
     */
    @Column(length = 20)
    private String telephone;
    
    /**
     * Indique si le médecin est actuellement disponible pour recevoir des rendez-vous.
     */
    @Column(nullable = false)
    private Boolean disponible = true;
    
    /**
     * Date de création de la fiche médecin (gérée automatiquement).
     */
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;
}

