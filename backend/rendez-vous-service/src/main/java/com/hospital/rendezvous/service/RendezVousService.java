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
public class RendezVousService {
    
    @Autowired
    private RendezVousRepository rendezVousRepository;
    
    @Autowired
    private MedecinRepository medecinRepository;
    
    public List<RendezVousDTO> getAllRendezVous() {
        return rendezVousRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    public RendezVousDTO getRendezVousById(Long id) {
        RendezVous rendezVous = rendezVousRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("RendezVous", "id", id));
        return convertToDTO(rendezVous);
    }
    
    public List<RendezVousDTO> getRendezVousByPatient(Long patientId) {
        return rendezVousRepository.findByPatientId(patientId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    public List<RendezVousDTO> getRendezVousByMedecin(Long medecinId) {
        return rendezVousRepository.findByMedecinId(medecinId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    public List<RendezVousDTO> getRendezVousByDate(LocalDate date) {
        return rendezVousRepository.findByDateRdv(date).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
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
    
    @Transactional
    public RendezVousDTO updateStatut(Long id, RendezVous.StatutRendezVous statut) {
        RendezVous rendezVous = rendezVousRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("RendezVous", "id", id));
        
        rendezVous.setStatut(statut);
        rendezVous = rendezVousRepository.save(rendezVous);
        return convertToDTO(rendezVous);
    }
    
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

