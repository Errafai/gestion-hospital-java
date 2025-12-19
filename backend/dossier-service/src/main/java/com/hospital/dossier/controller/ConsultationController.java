package com.hospital.dossier.controller;

import com.hospital.dossier.dto.ConsultationDTO;
import com.hospital.dossier.service.ConsultationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/consultations")
@CrossOrigin(origins = "*")
public class ConsultationController {
    
    @Autowired
    private ConsultationService consultationService;
    
    @GetMapping("/{id}")
    public ResponseEntity<ConsultationDTO> getConsultationById(@PathVariable Long id) {
        ConsultationDTO consultation = consultationService.getConsultationById(id);
        return ResponseEntity.ok(consultation);
    }
    
    @PostMapping
    public ResponseEntity<ConsultationDTO> createConsultation(@Valid @RequestBody ConsultationDTO consultationDTO) {
        ConsultationDTO created = consultationService.createConsultation(consultationDTO);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ConsultationDTO> updateConsultation(@PathVariable Long id, 
                                                               @Valid @RequestBody ConsultationDTO consultationDTO) {
        ConsultationDTO updated = consultationService.updateConsultation(id, consultationDTO);
        return ResponseEntity.ok(updated);
    }
    
    @GetMapping("/{id}/prescriptions")
    public ResponseEntity<?> getPrescriptions(@PathVariable Long id) {
        return ResponseEntity.ok("Prescriptions endpoint");
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConsultation(@PathVariable Long id) {
        consultationService.deleteConsultation(id);
        return ResponseEntity.noContent().build();
    }
}

