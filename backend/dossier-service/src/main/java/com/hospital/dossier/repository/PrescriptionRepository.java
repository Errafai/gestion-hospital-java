package com.hospital.dossier.repository;

import com.hospital.dossier.entity.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
/**
 * Repository pour l'entité Prescription.
 */
public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {
    
    /**
     * Trouve les prescriptions associées à une consultation.
     * @param consultationId ID de la consultation.
     * @return Liste des prescriptions.
     */
    List<Prescription> findByConsultationId(Long consultationId);
}

