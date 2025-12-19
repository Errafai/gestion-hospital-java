package com.hospital.rendezvous.repository;

import com.hospital.rendezvous.entity.Medecin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
/**
 * Repository pour l'entité Médecin.
 * Gère l'accès aux données des médecins.
 */
public interface MedecinRepository extends JpaRepository<Medecin, Long> {
    
    /**
     * Trouve un médecin par son numéro d'ordre.
     * @param numeroOrdre Le numéro d'ordre.
     * @return Optional contenant le médecin si trouvé.
     */
    Optional<Medecin> findByNumeroOrdre(String numeroOrdre);
    
    /**
     * Liste les médecins par spécialité.
     * @param specialite La spécialité recherchée.
     * @return Liste des médecins correspondants.
     */
    List<Medecin> findBySpecialite(String specialite);
    
    /**
     * Liste les médecins selon leur disponibilité.
     * @param disponible Statut de disponibilité.
     * @return Liste des médecins.
     */
    List<Medecin> findByDisponible(Boolean disponible);
    
    /**
     * Trouve un médecin par l'ID de son compte utilisateur associé.
     * @param userId L'ID de l'utilisateur.
     * @return Optional contenant le médecin.
     */
    Optional<Medecin> findByUserId(Long userId);
    
    /**
     * Vérifie si un numéro d'ordre existe déjà.
     * @param numeroOrdre Le numéro d'ordre à vérifier.
     * @return true si existe, false sinon.
     */
    Boolean existsByNumeroOrdre(String numeroOrdre);
}

