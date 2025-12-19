package com.hospital.dossier.repository;

import com.hospital.dossier.entity.Consultation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
/**
 * Repository pour l'entité Consultation.
 */
public interface ConsultationRepository extends JpaRepository<Consultation, Long> {
    
    /**
     * Trouve les consultations liées à un dossier médical.
     * @param dossierMedicalId ID du dossier.
     * @return Liste des consultations.
     */
    List<Consultation> findByDossierMedicalId(Long dossierMedicalId);
    
    /**
     * Trouve les consultations effectuées par un médecin.
     * @param medecinId ID du médecin.
     * @return Liste des consultations.
     */
    List<Consultation> findByMedecinId(Long medecinId);
}

