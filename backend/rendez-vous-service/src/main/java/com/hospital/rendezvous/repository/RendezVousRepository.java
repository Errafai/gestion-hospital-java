package com.hospital.rendezvous.repository;

import com.hospital.rendezvous.entity.RendezVous;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface RendezVousRepository extends JpaRepository<RendezVous, Long> {
    
    List<RendezVous> findByPatientId(Long patientId);
    
    List<RendezVous> findByMedecinId(Long medecinId);
    
    List<RendezVous> findByDateRdv(LocalDate date);
    
    @Query("SELECT r FROM RendezVous r WHERE r.medecinId = :medecinId " +
           "AND r.dateRdv = :date " +
           "AND ((r.heureDebut <= :heureDebut AND r.heureFin > :heureDebut) OR " +
           "(r.heureDebut < :heureFin AND r.heureFin >= :heureFin) OR " +
           "(r.heureDebut >= :heureDebut AND r.heureFin <= :heureFin))")
    List<RendezVous> findConflictingRendezVous(
        @Param("medecinId") Long medecinId,
        @Param("date") LocalDate date,
        @Param("heureDebut") LocalTime heureDebut,
        @Param("heureFin") LocalTime heureFin
    );
}

