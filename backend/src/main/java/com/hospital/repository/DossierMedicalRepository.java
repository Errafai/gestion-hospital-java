package com.hospital.repository;

import com.hospital.entity.DossierMedical;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DossierMedicalRepository extends JpaRepository<DossierMedical, Long> {
    
    Optional<DossierMedical> findByPatientId(Long patientId);
    
    Optional<DossierMedical> findByNumeroDossier(String numeroDossier);
    
    Boolean existsByNumeroDossier(String numeroDossier);
}

