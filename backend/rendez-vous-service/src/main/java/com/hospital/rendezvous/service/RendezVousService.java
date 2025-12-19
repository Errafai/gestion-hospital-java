package com.hospital.rendezvous.service;

import com.hospital.rendezvous.dto.RendezVousDTO;
import com.hospital.rendezvous.entity.Medecin;
import com.hospital.rendezvous.entity.RendezVous;
import com.hospital.rendezvous.exception.BadRequestException;
import com.hospital.rendezvous.exception.ResourceNotFoundException;
import com.hospital.rendezvous.repository.MedecinRepository;
import com.hospital.rendezvous.repository.RendezVousRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
/**
 * Service gérant la planification et le suivi des rendez-vous.
 * Vérifie les conflits d'horaires et la disponibilité des médecins.
 */
public class RendezVousService {
    
    @Autowired
    private RendezVousRepository rendezVousRepository;
    
    @Autowired
    private MedecinRepository medecinRepository;
    
    /**
     * Récupère tous les rendez-vous.
     * @return Liste de DTOs.
     */
    public List<RendezVousDTO> getAllRendezVous() {
        return rendezVousRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    /**
     * Récupère un rendez-vous par ID.
     * @param id Identifiant.
     * @return DTO du rendez-vous.
     * @throws ResourceNotFoundException Si non trouvé.
     */
    public RendezVousDTO getRendezVousById(Long id) {
        RendezVous rendezVous = rendezVousRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("RendezVous", "id", id));
        return convertToDTO(rendezVous);
    }
    
    /**
     * Historique des rendez-vous d'un patient.
     * @param patientId ID du patient.
     * @return Liste des rendez-vous.
     */
    public List<RendezVousDTO> getRendezVousByPatient(Long patientId) {
        return rendezVousRepository.findByPatientId(patientId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    /**
     * Agenda des rendez-vous d'un médecin.
     * @param medecinId ID du médecin.
     * @return Liste des rendez-vous.
     */
    public List<RendezVousDTO> getRendezVousByMedecin(Long medecinId) {
        return rendezVousRepository.findByMedecinId(medecinId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    /**
     * Rendez-vous pour une date specifique.
     * @param date Date du rendez-vous.
     * @return Liste des rendez-vous.
     */
    public List<RendezVousDTO> getRendezVousByDate(LocalDate date) {
        return rendezVousRepository.findByDateRdv(date).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    /**
     * Planifie un nouveau rendez-vous.
     * Vérifie :
     * 1. Que le médecin existe et est disponible.
     * 2. Qu'il n'y a pas de conflit d'horaire (chevauchement) pour ce médecin à cette date.
     * @param rendezVousDTO Données du rendez-vous.
     * @return Le rendez-vous créé.
     * @throws BadRequestException Si médecin non disponible ou créneau occupé.
     */
    @Transactional
    public RendezVousDTO createRendezVous(RendezVousDTO rendezVousDTO) {
        Medecin medecin = medecinRepository.findById(rendezVousDTO.getMedecinId())
                .orElseThrow(() -> new ResourceNotFoundException("Medecin", "id", rendezVousDTO.getMedecinId()));
        
        if (!medecin.getDisponible()) {
            throw new BadRequestException("Médecin is not available");
        }
        
        List<RendezVous> conflicts = rendezVousRepository.findConflictingRendezVous(
            rendezVousDTO.getMedecinId(),
            rendezVousDTO.getDateRdv(),
            rendezVousDTO.getHeureDebut(),
            rendezVousDTO.getHeureFin()
        );
        
        if (!conflicts.isEmpty()) {
            throw new BadRequestException("Time slot is already booked");
        }
        
        RendezVous rendezVous = convertToEntity(rendezVousDTO);
        rendezVous.setStatut(rendezVousDTO.getStatut() != null ? 
            rendezVousDTO.getStatut() : RendezVous.StatutRendezVous.PLANIFIE);
        
        rendezVous = rendezVousRepository.save(rendezVous);
        return convertToDTO(rendezVous);
    }
    
    /**
     * Modifie un rendez-vous existant.
     * Revérifie les conflits d'horaires en excluant le rendez-vous actuel.
     * @param id ID du rendez-vous à modifier.
     * @param rendezVousDTO Nouvelles données.
     * @return Rendez-vous mis à jour.
     */
    @Transactional
    public RendezVousDTO updateRendezVous(Long id, RendezVousDTO rendezVousDTO) {
        RendezVous rendezVous = rendezVousRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("RendezVous", "id", id));
        
        Medecin medecin = medecinRepository.findById(rendezVousDTO.getMedecinId())
                .orElseThrow(() -> new ResourceNotFoundException("Medecin", "id", rendezVousDTO.getMedecinId()));
        
        List<RendezVous> conflicts = rendezVousRepository.findConflictingRendezVous(
            rendezVousDTO.getMedecinId(),
            rendezVousDTO.getDateRdv(),
            rendezVousDTO.getHeureDebut(),
            rendezVousDTO.getHeureFin()
        );
        conflicts.removeIf(r -> r.getId().equals(id));
        
        if (!conflicts.isEmpty()) {
            throw new BadRequestException("Time slot is already booked");
        }
        
        rendezVous.setPatientId(rendezVousDTO.getPatientId());
        rendezVous.setMedecinId(rendezVousDTO.getMedecinId());
        rendezVous.setDateRdv(rendezVousDTO.getDateRdv());
        rendezVous.setHeureDebut(rendezVousDTO.getHeureDebut());
        rendezVous.setHeureFin(rendezVousDTO.getHeureFin());
        rendezVous.setMotif(rendezVousDTO.getMotif());
        if (rendezVousDTO.getStatut() != null) {
            rendezVous.setStatut(rendezVousDTO.getStatut());
        }
        rendezVous.setNotes(rendezVousDTO.getNotes());
        
        rendezVous = rendezVousRepository.save(rendezVous);
        return convertToDTO(rendezVous);
    }
    
    /**
     * Change uniquement le statut du rendez-vous (ex: CONFIRME, ANNULE).
     * @param id ID du rendez-vous.
     * @param statut Nouveau statut.
     * @return Rendez-vous mis à jour.
     */
    @Transactional
    public RendezVousDTO updateStatut(Long id, RendezVous.StatutRendezVous statut) {
        RendezVous rendezVous = rendezVousRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("RendezVous", "id", id));
        
        rendezVous.setStatut(statut);
        rendezVous = rendezVousRepository.save(rendezVous);
        return convertToDTO(rendezVous);
    }
    
    /**
     * Supprime un rendez-vous.
     * @param id Identifiant.
     */
    @Transactional
    public void deleteRendezVous(Long id) {
        RendezVous rendezVous = rendezVousRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("RendezVous", "id", id));
        rendezVousRepository.delete(rendezVous);
    }
    
    private RendezVousDTO convertToDTO(RendezVous rendezVous) {
        RendezVousDTO dto = new RendezVousDTO();
        dto.setId(rendezVous.getId());
        dto.setPatientId(rendezVous.getPatientId());
        dto.setMedecinId(rendezVous.getMedecinId());
        dto.setDateRdv(rendezVous.getDateRdv());
        dto.setHeureDebut(rendezVous.getHeureDebut());
        dto.setHeureFin(rendezVous.getHeureFin());
        dto.setMotif(rendezVous.getMotif());
        dto.setStatut(rendezVous.getStatut());
        dto.setNotes(rendezVous.getNotes());
        return dto;
    }
    
    private RendezVous convertToEntity(RendezVousDTO dto) {
        RendezVous rendezVous = new RendezVous();
        rendezVous.setPatientId(dto.getPatientId());
        rendezVous.setMedecinId(dto.getMedecinId());
        rendezVous.setDateRdv(dto.getDateRdv());
        rendezVous.setHeureDebut(dto.getHeureDebut());
        rendezVous.setHeureFin(dto.getHeureFin());
        rendezVous.setMotif(dto.getMotif());
        rendezVous.setStatut(dto.getStatut() != null ? dto.getStatut() : RendezVous.StatutRendezVous.PLANIFIE);
        rendezVous.setNotes(dto.getNotes());
        return rendezVous;
    }
}

