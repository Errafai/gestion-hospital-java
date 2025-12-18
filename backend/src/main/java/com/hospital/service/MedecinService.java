package com.hospital.service;

import com.hospital.dto.MedecinDTO;
import com.hospital.entity.Medecin;
import com.hospital.entity.User;
import com.hospital.exception.BadRequestException;
import com.hospital.exception.ResourceNotFoundException;
import com.hospital.repository.MedecinRepository;
import com.hospital.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MedecinService {
    
    @Autowired
    private MedecinRepository medecinRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    public List<MedecinDTO> getAllMedecins() {
        return medecinRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    public MedecinDTO getMedecinById(Long id) {
        Medecin medecin = medecinRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Medecin", "id", id));
        return convertToDTO(medecin);
    }
    
    public List<MedecinDTO> getMedecinsBySpecialite(String specialite) {
        return medecinRepository.findBySpecialite(specialite).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    public List<MedecinDTO> getAvailableMedecins() {
        return medecinRepository.findByDisponible(true).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    @Transactional
    public MedecinDTO createMedecin(MedecinDTO medecinDTO) {
        if (medecinRepository.existsByNumeroOrdre(medecinDTO.getNumeroOrdre())) {
            throw new BadRequestException("Numéro ordre already exists");
        }
        
        User user = userRepository.findById(medecinDTO.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", medecinDTO.getUserId()));
        
        if (medecinRepository.findByUserId(medecinDTO.getUserId()).isPresent()) {
            throw new BadRequestException("User already has a medecin profile");
        }
        
        Medecin medecin = new Medecin();
        medecin.setUser(user);
        medecin.setNumeroOrdre(medecinDTO.getNumeroOrdre());
        medecin.setSpecialite(medecinDTO.getSpecialite());
        medecin.setTelephone(medecinDTO.getTelephone());
        medecin.setDisponible(medecinDTO.getDisponible() != null ? medecinDTO.getDisponible() : true);
        
        medecin = medecinRepository.save(medecin);
        return convertToDTO(medecin);
    }
    
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
    
    @Transactional
    public void deleteMedecin(Long id) {
        Medecin medecin = medecinRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Medecin", "id", id));
        medecinRepository.delete(medecin);
    }
    
    private MedecinDTO convertToDTO(Medecin medecin) {
        MedecinDTO dto = new MedecinDTO();
        dto.setId(medecin.getId());
        dto.setUserId(medecin.getUser().getId());
        dto.setNumeroOrdre(medecin.getNumeroOrdre());
        dto.setSpecialite(medecin.getSpecialite());
        dto.setTelephone(medecin.getTelephone());
        dto.setDisponible(medecin.getDisponible());
        return dto;
    }
}

