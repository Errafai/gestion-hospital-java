package com.hospital.dossier.controller;

import com.hospital.dossier.dto.ConsultationDTO;
import com.hospital.dossier.dto.DossierMedicalDTO;
import com.hospital.dossier.service.ConsultationService;
import com.hospital.dossier.service.DossierMedicalService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Contrôleur REST pour la gestion des dossiers médicaux.
 * Accessible via l'API Gateway sous /api/dossiers/**.
 */
@RestController
@RequestMapping("/dossiers")
@CrossOrigin(origins = "*")
public class DossierMedicalController {
    
    @Autowired
    private DossierMedicalService dossierMedicalService;
    
    @Autowired
    private ConsultationService consultationService;
    
    /**
     * Récupère un dossier médical par son identifiant technique.
     */
    @GetMapping("/{id}")
    public ResponseEntity<DossierMedicalDTO> getDossierById(@PathVariable Long id) {
        DossierMedicalDTO dossier = dossierMedicalService.getDossierById(id);
        return ResponseEntity.ok(dossier);
    }
    
    /**
     * Récupère le dossier médical associé à un patient donné.
     * Chaque patient doit avoir au maximum un seul dossier médical.
     */
    @GetMapping("/patient/{patientId}")
    public ResponseEntity<DossierMedicalDTO> getDossierByPatient(@PathVariable Long patientId) {
        DossierMedicalDTO dossier = dossierMedicalService.getDossierByPatient(patientId);
        return ResponseEntity.ok(dossier);
    }
    
    /**
     * Crée un nouveau dossier médical pour un patient.
     * Vérifie qu'il n'existe pas déjà un dossier pour ce patient.
     */
    @PostMapping
    public ResponseEntity<DossierMedicalDTO> createDossier(@Valid @RequestBody DossierMedicalDTO dossierDTO) {
        DossierMedicalDTO created = dossierMedicalService.createDossier(dossierDTO);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }
    
    /**
     * Met à jour les informations (antécédents) d'un dossier médical existant.
     */
    @PutMapping("/{id}")
    public ResponseEntity<DossierMedicalDTO> updateDossier(@PathVariable Long id, 
                                                           @Valid @RequestBody DossierMedicalDTO dossierDTO) {
        DossierMedicalDTO updated = dossierMedicalService.updateDossier(id, dossierDTO);
        return ResponseEntity.ok(updated);
    }
    
    /**
     * Récupère la liste des consultations associées à un dossier médical.
     */
    @GetMapping("/{id}/consultations")
    public ResponseEntity<List<ConsultationDTO>> getConsultations(@PathVariable Long id) {
        List<ConsultationDTO> consultations = consultationService.getConsultationsByDossier(id);
        return ResponseEntity.ok(consultations);
    }
    
    /**
     * (Placeholder) Endpoint prévu pour renvoyer les documents liés au dossier médical.
     * À implémenter ultérieurement si nécessaire.
     */
    @GetMapping("/{id}/documents")
    public ResponseEntity<?> getDocuments(@PathVariable Long id) {
        return ResponseEntity.ok("Documents endpoint");
    }
}

