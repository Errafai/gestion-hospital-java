package com.hospital.controller;

import com.hospital.dto.PatientDTO;
import com.hospital.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
@CrossOrigin(origins = "*")
public class PatientController {
    
    @Autowired
    private PatientService patientService;
    
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'MEDECIN', 'RECEPTIONNISTE')")
    public ResponseEntity<?> getAllPatients(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        if (page >= 0 && size > 0) {
            Pageable pageable = PageRequest.of(page, size);
            Page<PatientDTO> patients = patientService.getAllPatients(pageable);
            return ResponseEntity.ok(patients);
        } else {
            List<PatientDTO> patients = patientService.getAllPatients();
            return ResponseEntity.ok(patients);
        }
    }
    
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MEDECIN', 'RECEPTIONNISTE')")
    public ResponseEntity<PatientDTO> getPatientById(@PathVariable Long id) {
        PatientDTO patient = patientService.getPatientById(id);
        return ResponseEntity.ok(patient);
    }
    
    @GetMapping("/numero/{numero}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MEDECIN', 'RECEPTIONNISTE')")
    public ResponseEntity<PatientDTO> getPatientByNumero(@PathVariable String numero) {
        PatientDTO patient = patientService.getPatientByNumero(numero);
        return ResponseEntity.ok(patient);
    }
    
    @GetMapping("/search")
    @PreAuthorize("hasAnyRole('ADMIN', 'MEDECIN', 'RECEPTIONNISTE')")
    public ResponseEntity<List<PatientDTO>> searchPatients(@RequestParam String q) {
        List<PatientDTO> patients = patientService.searchPatients(q);
        return ResponseEntity.ok(patients);
    }
    
    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'RECEPTIONNISTE')")
    public ResponseEntity<PatientDTO> createPatient(@Valid @RequestBody PatientDTO patientDTO) {
        PatientDTO created = patientService.createPatient(patientDTO);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }
    
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'RECEPTIONNISTE')")
    public ResponseEntity<PatientDTO> updatePatient(@PathVariable Long id, 
                                                   @Valid @RequestBody PatientDTO patientDTO) {
        PatientDTO updated = patientService.updatePatient(id, patientDTO);
        return ResponseEntity.ok(updated);
    }
    
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
        patientService.deletePatient(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/{id}/dossier")
    @PreAuthorize("hasAnyRole('ADMIN', 'MEDECIN', 'RECEPTIONNISTE')")
    public ResponseEntity<?> getPatientDossier(@PathVariable Long id) {
        // This will be handled by DossierMedicalController
        return ResponseEntity.ok("Dossier endpoint");
    }
}

