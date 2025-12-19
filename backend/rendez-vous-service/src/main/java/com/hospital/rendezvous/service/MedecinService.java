package com.hospital.rendezvous.service;

import com.hospital.rendezvous.dto.MedecinDTO;
import com.hospital.rendezvous.entity.Medecin;
import com.hospital.rendezvous.exception.BadRequestException;
import com.hospital.rendezvous.exception.ResourceNotFoundException;
import com.hospital.rendezvous.repository.MedecinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
/**
 * Service gérant la logique métier pour les médecins.
 * Permet de gérer la disponibilité, la création de profils et la recherche.
 */
public class MedecinService {
    
    @Autowired
    private MedecinRepository medecinRepository;
    
    /**
     * Récupère la liste de tous les médecins.
     * @return Liste de DTOs.
     */
    public List<MedecinDTO> getAllMedecins() {
        return medecinRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    /**
     * Récupère un médecin par son ID.
     * @param id Identifiant du médecin.
     * @return Le DTO du médecin trouvé.
     * @throws ResourceNotFoundException Si le médecin n'existe pas.
     */
    public MedecinDTO getMedecinById(Long id) {
        Medecin medecin = medecinRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Medecin", "id", id));
        return convertToDTO(medecin);
    }
    
    /**
     * Recherche les médecins par spécialité.
     * @param specialite La spécialité recherchée.
     * @return Liste des médecins correspondants.
     */
    public List<MedecinDTO> getMedecinsBySpecialite(String specialite) {
        return medecinRepository.findBySpecialite(specialite).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    /**
     * Récupère uniquement les médecins disponibles.
     * @return Liste des médecins disponibles.
     */
    public List<MedecinDTO> getAvailableMedecins() {
        return medecinRepository.findByDisponible(true).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    /**
     * Crée un nouveau profil médecin.
     * Vérifie l'unicité du numéro d'ordre et si l'utilisateur a déjà un profil.
     * @param medecinDTO Les données du médecin.
     * @return Le médecin créé.
     * @throws BadRequestException Si le numéro d'ordre existe ou l'utilisateur est déjà médecin.
     */
    @Transactional
    public MedecinDTO createMedecin(MedecinDTO medecinDTO) {
        if (medecinRepository.existsByNumeroOrdre(medecinDTO.getNumeroOrdre())) {
            throw new BadRequestException("Numéro ordre already exists");
        }
        
        if (medecinRepository.findByUserId(medecinDTO.getUserId()).isPresent()) {
            throw new BadRequestException("User already has a medecin profile");
        }
        
        Medecin medecin = new Medecin();
        medecin.setUserId(medecinDTO.getUserId());
        medecin.setNumeroOrdre(medecinDTO.getNumeroOrdre());
        medecin.setSpecialite(medecinDTO.getSpecialite());
        medecin.setTelephone(medecinDTO.getTelephone());
        medecin.setDisponible(medecinDTO.getDisponible() != null ? medecinDTO.getDisponible() : true);
        
        medecin = medecinRepository.save(medecin);
        return convertToDTO(medecin);
    }
    
    /**
     * Met à jour les informations d'un médecin.
     * @param id L'identifiant du médecin.
     * @param medecinDTO Les nouvelles données.
     * @return Le médecin mis à jour.
     */
    @Transactional
    public MedecinDTO updateMedecin(Long id, MedecinDTO medecinDTO) {
        Medecin medecin = medecinRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Medecin", "id", id));
        
        if (!medecin.getNumeroOrdre().equals(medecinDTO.getNumeroOrdre())) {
            if (medecinRepository.existsByNumeroOrdre(medecinDTO.getNumeroOrdre())) {
                throw new BadRequestException("Numéro ordre already exists");
            }
        }
        
        medecin.setNumeroOrdre(medecinDTO.getNumeroOrdre());
        medecin.setSpecialite(medecinDTO.getSpecialite());
        medecin.setTelephone(medecinDTO.getTelephone());
        if (medecinDTO.getDisponible() != null) {
            medecin.setDisponible(medecinDTO.getDisponible());
        }
        
        medecin = medecinRepository.save(medecin);
        return convertToDTO(medecin);
    }
    
    /**
     * Supprime un médecin.
     * @param id L'identifiant du médecin.
     */
    @Transactional
    public void deleteMedecin(Long id) {
        Medecin medecin = medecinRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Medecin", "id", id));
        medecinRepository.delete(medecin);
    }
    
    private MedecinDTO convertToDTO(Medecin medecin) {
        MedecinDTO dto = new MedecinDTO();
        dto.setId(medecin.getId());
        dto.setUserId(medecin.getUserId());
        dto.setNumeroOrdre(medecin.getNumeroOrdre());
        dto.setSpecialite(medecin.getSpecialite());
        dto.setTelephone(medecin.getTelephone());
        dto.setDisponible(medecin.getDisponible());
        return dto;
    }
}

