package com.hospital.dossier.repository;

import com.hospital.dossier.entity.DossierMedical;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
/**
 * Repository pour l'entité DossierMedical.
 * Permet l'accès aux dossiers médicaux par patient ou numéro de dossier.
 */
public interface DossierMedicalRepository extends JpaRepository<DossierMedical, Long> {
    
    /**
     * Recherche le dossier médical d'un patient.
     * @param patientId ID du patient.
     * @return Optional contenant le dossier.
     */
    Optional<DossierMedical> findByPatientId(Long patientId);
    
    /**
     * Recherche un dossier par son numéro unique.
     * @param numeroDossier Le numéro de dossier.
     * @return Optional contenant le dossier.
     */
    Optional<DossierMedical> findByNumeroDossier(String numeroDossier);
    
    /**
     * Vérifie l'existence d'un dossier par son numéro.
     * @param numeroDossier Le numéro à vérifier.
     * @return true si le dossier existe.
     */
    Boolean existsByNumeroDossier(String numeroDossier);
}

