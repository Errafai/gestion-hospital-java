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
/**
 * Repository pour l'entité RendezVous.
 * Gère la persistance et les requêtes spécifiques aux rendez-vous.
 */
public interface RendezVousRepository extends JpaRepository<RendezVous, Long> {
    
    /**
     * Trouve les rendez-vous d'un patient.
     * @param patientId L'ID du patient.
     * @return Liste des rendez-vous.
     */
    List<RendezVous> findByPatientId(Long patientId);
    
    /**
     * Trouve les rendez-vous d'un médecin.
     * @param medecinId L'ID du médecin.
     * @return Liste des rendez-vous.
     */
    List<RendezVous> findByMedecinId(Long medecinId);
    
    /**
     * Trouve les rendez-vous pour une date donnée.
     * @param date La date recherchée.
     * @return Liste des rendez-vous.
     */
    List<RendezVous> findByDateRdv(LocalDate date);
    
    /**
     * Vérifie les conflits de planning pour un médecin donné.
     * Recherche les rendez-vous existants qui chevauchent la plage horaire proposée.
     * @param medecinId L'ID du médecin.
     * @param date La date du rendez-vous.
     * @param heureDebut Heure de début souhaitée.
     * @param heureFin Heure de fin souhaitée.
     * @return Liste des rendez-vous conflictuels.
     */
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

