package com.hospital.dossier.controller;

import com.hospital.dossier.dto.ConsultationDTO;
import com.hospital.dossier.service.ConsultationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Contrôleur REST pour la gestion des consultations médicales.
 * Accessible via l'API Gateway sous /api/consultations/**.
 */
@RestController
@RequestMapping("/consultations")
@CrossOrigin(origins = "*")
public class ConsultationController {
    
    @Autowired
    private ConsultationService consultationService;
    
    /**
     * Récupère les détails d'une consultation à partir de son identifiant.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ConsultationDTO> getConsultationById(@PathVariable Long id) {
        ConsultationDTO consultation = consultationService.getConsultationById(id);
        return ResponseEntity.ok(consultation);
    }
    
    /**
     * Crée une nouvelle consultation pour un dossier médical donné.
     */
    @PostMapping
    public ResponseEntity<ConsultationDTO> createConsultation(@Valid @RequestBody ConsultationDTO consultationDTO) {
        ConsultationDTO created = consultationService.createConsultation(consultationDTO);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }
    
    /**
     * Met à jour les informations d'une consultation existante.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ConsultationDTO> updateConsultation(@PathVariable Long id, 
                                                               @Valid @RequestBody ConsultationDTO consultationDTO) {
        ConsultationDTO updated = consultationService.updateConsultation(id, consultationDTO);
        return ResponseEntity.ok(updated);
    }
    
    /**
     * (Placeholder) Endpoint prévu pour renvoyer les prescriptions liées à une consultation.
     * À implémenter ultérieurement si nécessaire.
     */
    @GetMapping("/{id}/prescriptions")
    public ResponseEntity<?> getPrescriptions(@PathVariable Long id) {
        return ResponseEntity.ok("Prescriptions endpoint");
    }
    
    /**
     * Supprime définitivement une consultation.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConsultation(@PathVariable Long id) {
        consultationService.deleteConsultation(id);
        return ResponseEntity.noContent().build();
    }
}

