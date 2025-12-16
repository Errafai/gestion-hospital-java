package com.hospital.repository;

import com.hospital.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    
    Optional<Patient> findByNumeroPatient(String numeroPatient);
    
    Optional<Patient> findByCin(String cin);
    
    @Query("SELECT p FROM Patient p WHERE " +
           "LOWER(p.nom) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
           "LOWER(p.prenom) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
           "LOWER(p.cin) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
           "LOWER(p.numeroPatient) LIKE LOWER(CONCAT('%', :query, '%'))")
    List<Patient> searchPatients(@Param("query") String query);
    
    Boolean existsByNumeroPatient(String numeroPatient);
    
    Boolean existsByCin(String cin);
}

