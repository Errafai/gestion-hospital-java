package com.hospital.dossier.repository;

import com.hospital.dossier.entity.Consultation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsultationRepository extends JpaRepository<Consultation, Long> {
    
    List<Consultation> findByDossierMedicalId(Long dossierMedicalId);
    
    List<Consultation> findByMedecinId(Long medecinId);
}

