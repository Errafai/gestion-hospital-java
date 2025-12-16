package com.hospital.service;

import com.hospital.dto.DossierMedicalDTO;
import com.hospital.entity.DossierMedical;
import com.hospital.entity.Patient;
import com.hospital.exception.BadRequestException;
import com.hospital.exception.ResourceNotFoundException;
import com.hospital.repository.DossierMedicalRepository;
import com.hospital.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class DossierMedicalService {
    
    @Autowired
    private DossierMedicalRepository dossierMedicalRepository;
    
    @Autowired
    private PatientRepository patientRepository;
    
    public DossierMedicalDTO getDossierById(Long id) {
        DossierMedical dossier = dossierMedicalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("DossierMedical", "id", id));
        return convertToDTO(dossier);
    }
    
    public DossierMedicalDTO getDossierByPatient(Long patientId) {
        DossierMedical dossier = dossierMedicalRepository.findByPatientId(patientId)
                .orElseThrow(() -> new ResourceNotFoundException("DossierMedical", "patientId", patientId));
        return convertToDTO(dossier);
    }
    
    @Transactional
    public DossierMedicalDTO createDossier(DossierMedicalDTO dossierDTO) {
        Patient patient = patientRepository.findById(dossierDTO.getPatientId())
                .orElseThrow(() -> new ResourceNotFoundException("Patient", "id", dossierDTO.getPatientId()));
        
        if (dossierMedicalRepository.findByPatientId(dossierDTO.getPatientId()).isPresent()) {
            throw new BadRequestException("Patient already has a medical dossier");
        }
        
        DossierMedical dossier = new DossierMedical();
        dossier.setPatient(patient);
        dossier.setNumeroDossier(dossierDTO.getNumeroDossier() != null ? 
            dossierDTO.getNumeroDossier() : "DOS-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        dossier.setAntecedentsMedicaux(dossierDTO.getAntecedentsMedicaux());
        dossier.setAntecedentsChirurgicaux(dossierDTO.getAntecedentsChirurgicaux());
        dossier.setAntecedentsFamiliaux(dossierDTO.getAntecedentsFamiliaux());
        
        dossier = dossierMedicalRepository.save(dossier);
        return convertToDTO(dossier);
    }
    
    @Transactional
    public DossierMedicalDTO updateDossier(Long id, DossierMedicalDTO dossierDTO) {
        DossierMedical dossier = dossierMedicalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("DossierMedical", "id", id));
        
        dossier.setAntecedentsMedicaux(dossierDTO.getAntecedentsMedicaux());
        dossier.setAntecedentsChirurgicaux(dossierDTO.getAntecedentsChirurgicaux());
        dossier.setAntecedentsFamiliaux(dossierDTO.getAntecedentsFamiliaux());
        
        dossier = dossierMedicalRepository.save(dossier);
        return convertToDTO(dossier);
    }
    
    private DossierMedicalDTO convertToDTO(DossierMedical dossier) {
        DossierMedicalDTO dto = new DossierMedicalDTO();
        dto.setId(dossier.getId());
        dto.setPatientId(dossier.getPatient().getId());
        dto.setNumeroDossier(dossier.getNumeroDossier());
        dto.setAntecedentsMedicaux(dossier.getAntecedentsMedicaux());
        dto.setAntecedentsChirurgicaux(dossier.getAntecedentsChirurgicaux());
        dto.setAntecedentsFamiliaux(dossier.getAntecedentsFamiliaux());
        return dto;
    }
}

