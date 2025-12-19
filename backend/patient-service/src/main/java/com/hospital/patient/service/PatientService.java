package com.hospital.patient.service;

import com.hospital.patient.dto.PatientDTO;
import com.hospital.patient.entity.Patient;
import com.hospital.patient.exception.BadRequestException;
import com.hospital.patient.exception.ResourceNotFoundException;
import com.hospital.patient.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
/**
 * Service gérant la logique métier pour les patients.
 * Fournit les méthodes pour créer, lire, mettre à jour et supprimer des patients.
 */
public class PatientService {
    
    @Autowired
    private PatientRepository patientRepository;
    
    /**
     * Récupère la liste de tous les patients.
     * @return Une liste de DTOs représentant tous les patients.
     */
    public List<PatientDTO> getAllPatients() {
        return patientRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    /**
     * Récupère une page de patients (pagination).
     * @param pageable Les informations de pagination.
     * @return Une page de DTOs.
     */
    public Page<PatientDTO> getAllPatients(Pageable pageable) {
        return patientRepository.findAll(pageable)
                .map(this::convertToDTO);
    }
    
    /**
     * Récupère un patient par son ID technique.
     * @param id L'identifiant du patient.
     * @return Le DTO du patient trouvé.
     * @throws ResourceNotFoundException si le patient n'existe pas.
     */
    public PatientDTO getPatientById(Long id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient", "id", id));
        return convertToDTO(patient);
    }
    
    /**
     * Récupère un patient par son numéro unique.
     * @param numero Le numéro de patient.
     * @return Le DTO du patient trouvé.
     * @throws ResourceNotFoundException si le patient n'existe pas.
     */
    public PatientDTO getPatientByNumero(String numero) {
        Patient patient = patientRepository.findByNumeroPatient(numero)
                .orElseThrow(() -> new ResourceNotFoundException("Patient", "numeroPatient", numero));
        return convertToDTO(patient);
    }
    
    /**
     * Recherche des patients selon une chaîne de caractères.
     * @param query La requête de recherche (nom, prénom, CIN...).
     * @return Une liste de DTOs correspondants.
     */
    public List<PatientDTO> searchPatients(String query) {
        return patientRepository.searchPatients(query).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    /**
     * Crée un nouveau patient dans le système.
     * Vérifie l'unicité du numéro de patient et de la CIN avant insertion.
     * @param patientDTO Les données du patient à créer.
     * @return Le DTO du patient créé.
     * @throws BadRequestException si le numéro ou la CIN existe déjà.
     */
    @Transactional
    public PatientDTO createPatient(PatientDTO patientDTO) {
        if (patientRepository.existsByNumeroPatient(patientDTO.getNumeroPatient())) {
            throw new BadRequestException("Numéro patient already exists");
        }
        
        if (patientDTO.getCin() != null && !patientDTO.getCin().isEmpty()) {
            if (patientRepository.existsByCin(patientDTO.getCin())) {
                throw new BadRequestException("CIN already exists");
            }
        }
        
        Patient patient = convertToEntity(patientDTO);
        patient = patientRepository.save(patient);
        return convertToDTO(patient);
    }
    
    /**
     * Met à jour les informations d'un patient existant.
     * @param id L'identifiant du patient à modifier.
     * @param patientDTO Les nouvelles données.
     * @return Le DTO mis à jour.
     * @throws ResourceNotFoundException si le patient n'est pas trouvé.
     * @throws BadRequestException si les nouvelles données violent l'unicité (numéro, CIN).
     */
    @Transactional
    public PatientDTO updatePatient(Long id, PatientDTO patientDTO) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient", "id", id));
        
        if (!patient.getNumeroPatient().equals(patientDTO.getNumeroPatient())) {
            if (patientRepository.existsByNumeroPatient(patientDTO.getNumeroPatient())) {
                throw new BadRequestException("Numéro patient already exists");
            }
        }
        
        if (patientDTO.getCin() != null && !patientDTO.getCin().isEmpty()) {
            if (!patient.getCin().equals(patientDTO.getCin()) && 
                patientRepository.existsByCin(patientDTO.getCin())) {
                throw new BadRequestException("CIN already exists");
            }
        }
        
        patient.setNumeroPatient(patientDTO.getNumeroPatient());
        patient.setNom(patientDTO.getNom());
        patient.setPrenom(patientDTO.getPrenom());
        patient.setDateNaissance(patientDTO.getDateNaissance());
        patient.setSexe(patientDTO.getSexe());
        patient.setCin(patientDTO.getCin());
        patient.setTelephone(patientDTO.getTelephone());
        patient.setEmail(patientDTO.getEmail());
        patient.setAdresse(patientDTO.getAdresse());
        patient.setVille(patientDTO.getVille());
        patient.setGroupeSanguin(patientDTO.getGroupeSanguin());
        patient.setAllergies(patientDTO.getAllergies());
        
        patient = patientRepository.save(patient);
        return convertToDTO(patient);
    }
    
    /**
     * Supprime un patient par son ID.
     * @param id L'identifiant du patient à supprimer.
     * @throws ResourceNotFoundException si le patient n'existe pas.
     */
    @Transactional
    public void deletePatient(Long id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient", "id", id));
        patientRepository.delete(patient);
    }
    
    private PatientDTO convertToDTO(Patient patient) {
        PatientDTO dto = new PatientDTO();
        dto.setId(patient.getId());
        dto.setNumeroPatient(patient.getNumeroPatient());
        dto.setNom(patient.getNom());
        dto.setPrenom(patient.getPrenom());
        dto.setDateNaissance(patient.getDateNaissance());
        dto.setSexe(patient.getSexe());
        dto.setCin(patient.getCin());
        dto.setTelephone(patient.getTelephone());
        dto.setEmail(patient.getEmail());
        dto.setAdresse(patient.getAdresse());
        dto.setVille(patient.getVille());
        dto.setGroupeSanguin(patient.getGroupeSanguin());
        dto.setAllergies(patient.getAllergies());
        return dto;
    }
    
    private Patient convertToEntity(PatientDTO dto) {
        Patient patient = new Patient();
        patient.setNumeroPatient(dto.getNumeroPatient());
        patient.setNom(dto.getNom());
        patient.setPrenom(dto.getPrenom());
        patient.setDateNaissance(dto.getDateNaissance());
        patient.setSexe(dto.getSexe());
        patient.setCin(dto.getCin());
        patient.setTelephone(dto.getTelephone());
        patient.setEmail(dto.getEmail());
        patient.setAdresse(dto.getAdresse());
        patient.setVille(dto.getVille());
        patient.setGroupeSanguin(dto.getGroupeSanguin());
        patient.setAllergies(dto.getAllergies());
        return patient;
    }
}

