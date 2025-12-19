package com.hospital.patient.repository;

import com.hospital.patient.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
/**
 * Interface Repository pour l'entité Patient.
 * Étend JpaRepository pour fournir les opérations CRUD standard.
 */
public interface PatientRepository extends JpaRepository<Patient, Long> {
    
    /**
     * Recherche un patient par son numéro unique.
     * @param numeroPatient Le numéro de patient à chercher.
     * @return Un Optional contenant le patient s'il est trouvé.
     */
    Optional<Patient> findByNumeroPatient(String numeroPatient);
    
    /**
     * Recherche un patient par sa CIN.
     * @param cin La CIN à chercher.
     * @return Un Optional contenant le patient s'il est trouvé.
     */
    Optional<Patient> findByCin(String cin);
    
    /**
     * Recherche avancée (case-insensitive) sur nom, prénom, CIN ou numéro patient.
     * @param query La chaîne de caractères à chercher.
     * @return Une liste de patients correspondant aux critères.
     */
    @Query("SELECT p FROM Patient p WHERE " +
           "LOWER(p.nom) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
           "LOWER(p.prenom) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
           "LOWER(p.cin) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
           "LOWER(p.numeroPatient) LIKE LOWER(CONCAT('%', :query, '%'))")
    List<Patient> searchPatients(@Param("query") String query);
    
    /**
     * Vérifie si un numéro de patient existe déjà.
     * @param numeroPatient Le numéro à vérifier.
     * @return true si le numéro existe, false sinon.
     */
    Boolean existsByNumeroPatient(String numeroPatient);
    
    /**
     * Vérifie si une CIN existe déjà.
     * @param cin La CIN à vérifier.
     * @return true si la CIN existe, false sinon.
     */
    Boolean existsByCin(String cin);
}

