package com.hospital.service;

import com.hospital.dto.ConsultationDTO;
import com.hospital.entity.Consultation;
import com.hospital.entity.DossierMedical;
import com.hospital.entity.Medecin;
import com.hospital.entity.RendezVous;
import com.hospital.exception.ResourceNotFoundException;
import com.hospital.repository.ConsultationRepository;
import com.hospital.repository.DossierMedicalRepository;
import com.hospital.repository.MedecinRepository;
import com.hospital.repository.RendezVousRepository;
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
    
    @Autowired
    private MedecinRepository medecinRepository;
    
    @Autowired
    private RendezVousRepository rendezVousRepository;
    
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
        
        Medecin medecin = medecinRepository.findById(consultationDTO.getMedecinId())
                .orElseThrow(() -> new ResourceNotFoundException("Medecin", "id", consultationDTO.getMedecinId()));
        
        Consultation consultation = new Consultation();
        consultation.setDossierMedical(dossier);
        consultation.setMedecin(medecin);
        consultation.setDateConsultation(consultationDTO.getDateConsultation());
        consultation.setSymptomes(consultationDTO.getSymptomes());
        consultation.setDiagnostic(consultationDTO.getDiagnostic());
        consultation.setTraitement(consultationDTO.getTraitement());
        consultation.setObservations(consultationDTO.getObservations());
        
        if (consultationDTO.getRendezVousId() != null) {
            RendezVous rendezVous = rendezVousRepository.findById(consultationDTO.getRendezVousId())
                    .orElseThrow(() -> new ResourceNotFoundException("RendezVous", "id", consultationDTO.getRendezVousId()));
            consultation.setRendezVous(rendezVous);
        }
        
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
        dto.setMedecinId(consultation.getMedecin().getId());
        dto.setDateConsultation(consultation.getDateConsultation());
        dto.setSymptomes(consultation.getSymptomes());
        dto.setDiagnostic(consultation.getDiagnostic());
        dto.setTraitement(consultation.getTraitement());
        dto.setObservations(consultation.getObservations());
        if (consultation.getRendezVous() != null) {
            dto.setRendezVousId(consultation.getRendezVous().getId());
        }
        return dto;
    }
}

