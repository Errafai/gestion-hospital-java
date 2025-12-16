package com.hospital.dossier.service;

import com.hospital.dossier.dto.ConsultationDTO;
import com.hospital.dossier.entity.Consultation;
import com.hospital.dossier.entity.DossierMedical;
import com.hospital.dossier.exception.ResourceNotFoundException;
import com.hospital.dossier.repository.ConsultationRepository;
import com.hospital.dossier.repository.DossierMedicalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConsultationService {
    
    @Autowired
    private ConsultationRepository consultationRepository;
    
    @Autowired
    private DossierMedicalRepository dossierMedicalRepository;
    
    public ConsultationDTO getConsultationById(Long id) {
        Consultation consultation = consultationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Consultation", "id", id));
        return convertToDTO(consultation);
    }
    
    public List<ConsultationDTO> getConsultationsByDossier(Long dossierId) {
        return consultationRepository.findByDossierMedicalId(dossierId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    public List<ConsultationDTO> getConsultationsByMedecin(Long medecinId) {
        return consultationRepository.findByMedecinId(medecinId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    @Transactional
    public ConsultationDTO createConsultation(ConsultationDTO consultationDTO) {
        DossierMedical dossier = dossierMedicalRepository.findById(consultationDTO.getDossierMedicalId())
                .orElseThrow(() -> new ResourceNotFoundException("DossierMedical", "id", consultationDTO.getDossierMedicalId()));
        
        Consultation consultation = new Consultation();
        consultation.setDossierMedical(dossier);
        consultation.setMedecinId(consultationDTO.getMedecinId());
        consultation.setRendezVousId(consultationDTO.getRendezVousId());
        consultation.setDateConsultation(consultationDTO.getDateConsultation());
        consultation.setSymptomes(consultationDTO.getSymptomes());
        consultation.setDiagnostic(consultationDTO.getDiagnostic());
        consultation.setTraitement(consultationDTO.getTraitement());
        consultation.setObservations(consultationDTO.getObservations());
        
        consultation = consultationRepository.save(consultation);
        return convertToDTO(consultation);
    }
    
    @Transactional
    public ConsultationDTO updateConsultation(Long id, ConsultationDTO consultationDTO) {
        Consultation consultation = consultationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Consultation", "id", id));
        
        consultation.setDateConsultation(consultationDTO.getDateConsultation());
        consultation.setSymptomes(consultationDTO.getSymptomes());
        consultation.setDiagnostic(consultationDTO.getDiagnostic());
        consultation.setTraitement(consultationDTO.getTraitement());
        consultation.setObservations(consultationDTO.getObservations());
        
        consultation = consultationRepository.save(consultation);
        return convertToDTO(consultation);
    }
    
    @Transactional
    public void deleteConsultation(Long id) {
        Consultation consultation = consultationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Consultation", "id", id));
        consultationRepository.delete(consultation);
    }
    
    private ConsultationDTO convertToDTO(Consultation consultation) {
        ConsultationDTO dto = new ConsultationDTO();
        dto.setId(consultation.getId());
        dto.setDossierMedicalId(consultation.getDossierMedical().getId());
        dto.setMedecinId(consultation.getMedecinId());
        dto.setDateConsultation(consultation.getDateConsultation());
        dto.setSymptomes(consultation.getSymptomes());
        dto.setDiagnostic(consultation.getDiagnostic());
        dto.setTraitement(consultation.getTraitement());
        dto.setObservations(consultation.getObservations());
        if (consultation.getRendezVousId() != null) {
            dto.setRendezVousId(consultation.getRendezVousId());
        }
        return dto;
    }
}

