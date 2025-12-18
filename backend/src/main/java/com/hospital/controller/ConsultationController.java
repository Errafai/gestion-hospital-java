package com.hospital.controller;

import com.hospital.dto.ConsultationDTO;
import com.hospital.service.ConsultationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/consultations")
@CrossOrigin(origins = "*")
public class ConsultationController {
    
    @Autowired
    private ConsultationService consultationService;
    
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MEDECIN', 'RECEPTIONNISTE')")
    public ResponseEntity<ConsultationDTO> getConsultationById(@PathVariable Long id) {
        ConsultationDTO consultation = consultationService.getConsultationById(id);
        return ResponseEntity.ok(consultation);
    }
    
    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'MEDECIN')")
    public ResponseEntity<ConsultationDTO> createConsultation(@Valid @RequestBody ConsultationDTO consultationDTO) {
        ConsultationDTO created = consultationService.createConsultation(consultationDTO);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }
    
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MEDECIN')")
    public ResponseEntity<ConsultationDTO> updateConsultation(@PathVariable Long id, 
                                                               @Valid @RequestBody ConsultationDTO consultationDTO) {
        ConsultationDTO updated = consultationService.updateConsultation(id, consultationDTO);
        return ResponseEntity.ok(updated);
    }
    
    @GetMapping("/{id}/prescriptions")
    @PreAuthorize("hasAnyRole('ADMIN', 'MEDECIN', 'RECEPTIONNISTE')")
    public ResponseEntity<?> getPrescriptions(@PathVariable Long id) {
        // TODO: Implement prescriptions logic
        return ResponseEntity.ok("Prescriptions endpoint");
    }
    
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteConsultation(@PathVariable Long id) {
        consultationService.deleteConsultation(id);
        return ResponseEntity.noContent().build();
    }
}

