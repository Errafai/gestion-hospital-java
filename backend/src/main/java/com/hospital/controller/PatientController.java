package com.hospital.controller;

import com.hospital.dto.PatientDto;
import com.hospital.service.PatientService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    private PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR', 'NURSE')")
    public ResponseEntity<Page<PatientDto>> getAllPatients(Pageable pageable) {
        return ResponseEntity.ok(patientService.getAllPatients(pageable));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR', 'NURSE')")
    public ResponseEntity<PatientDto> getPatientById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(patientService.getPatientById(id));
    }

    @GetMapping("/patient-number/{patientNumber}")
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR', 'NURSE')")
    public ResponseEntity<PatientDto> getPatientByPatientNumber(
            @PathVariable(name = "patientNumber") String patientNumber) {
        return ResponseEntity.ok(patientService.getPatientByPatientNumber(patientNumber));
    }

    @GetMapping("/search")
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR', 'NURSE')")
    public ResponseEntity<Page<PatientDto>> searchPatients(@RequestParam("q") String query, Pageable pageable) {
        return ResponseEntity.ok(patientService.searchPatients(query, pageable));
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'NURSE')")
    public ResponseEntity<PatientDto> createPatient(@RequestBody PatientDto patientDto) {
        return new ResponseEntity<>(patientService.createPatient(patientDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'NURSE')")
    public ResponseEntity<PatientDto> updatePatient(@PathVariable(name = "id") Long id,
            @RequestBody PatientDto patientDto) {
        return ResponseEntity.ok(patientService.updatePatient(id, patientDto));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deletePatient(@PathVariable(name = "id") Long id) {
        patientService.deletePatient(id);
        return ResponseEntity.ok("Patient deleted successfully");
    }

    // /api/patients/{id}/dossier requires DossierService or just return DTO if
    // implemented
    // I'll leave it as a TODO or implement if service supports it directly
}
