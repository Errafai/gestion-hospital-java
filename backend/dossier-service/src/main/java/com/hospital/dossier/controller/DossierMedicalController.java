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

@RestController
@RequestMapping("/dossiers")
@CrossOrigin(origins = "*")
public class DossierMedicalController {
    
    @Autowired
    private DossierMedicalService dossierMedicalService;
    
    @Autowired
    private ConsultationService consultationService;
    
    @GetMapping("/{id}")
    public ResponseEntity<DossierMedicalDTO> getDossierById(@PathVariable Long id) {
        DossierMedicalDTO dossier = dossierMedicalService.getDossierById(id);
        return ResponseEntity.ok(dossier);
    }
    
    @GetMapping("/patient/{patientId}")
    public ResponseEntity<DossierMedicalDTO> getDossierByPatient(@PathVariable Long patientId) {
        DossierMedicalDTO dossier = dossierMedicalService.getDossierByPatient(patientId);
        return ResponseEntity.ok(dossier);
    }
    
    @PostMapping
    public ResponseEntity<DossierMedicalDTO> createDossier(@Valid @RequestBody DossierMedicalDTO dossierDTO) {
        DossierMedicalDTO created = dossierMedicalService.createDossier(dossierDTO);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<DossierMedicalDTO> updateDossier(@PathVariable Long id, 
                                                           @Valid @RequestBody DossierMedicalDTO dossierDTO) {
        DossierMedicalDTO updated = dossierMedicalService.updateDossier(id, dossierDTO);
        return ResponseEntity.ok(updated);
    }
    
    @GetMapping("/{id}/consultations")
    public ResponseEntity<List<ConsultationDTO>> getConsultations(@PathVariable Long id) {
        List<ConsultationDTO> consultations = consultationService.getConsultationsByDossier(id);
        return ResponseEntity.ok(consultations);
    }
    
    @GetMapping("/{id}/documents")
    public ResponseEntity<?> getDocuments(@PathVariable Long id) {
        return ResponseEntity.ok("Documents endpoint");
    }
}

