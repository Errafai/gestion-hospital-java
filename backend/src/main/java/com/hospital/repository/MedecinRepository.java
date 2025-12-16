package com.hospital.repository;

import com.hospital.entity.Medecin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MedecinRepository extends JpaRepository<Medecin, Long> {
    
    Optional<Medecin> findByNumeroOrdre(String numeroOrdre);
    
    List<Medecin> findBySpecialite(String specialite);
    
    List<Medecin> findByDisponible(Boolean disponible);
    
    Optional<Medecin> findByUserId(Long userId);
    
    Boolean existsByNumeroOrdre(String numeroOrdre);
}

