package com.hospital.controller;

import com.hospital.dto.ConsultationDto;
import com.hospital.dto.PrescriptionDto;
import com.hospital.service.ConsultationService;
import com.hospital.service.PrescriptionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/consultations")
public class ConsultationController {

    private ConsultationService consultationService;
    private PrescriptionService prescriptionService;

    public ConsultationController(ConsultationService consultationService,
            PrescriptionService prescriptionService) {
        this.consultationService = consultationService;
        this.prescriptionService = prescriptionService;
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
    public ResponseEntity<List<ConsultationDto>> getAllConsultations() {
        return ResponseEntity.ok(consultationService.getAllConsultations());
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
    public ResponseEntity<ConsultationDto> createConsultation(@RequestBody ConsultationDto consultationDto) {
        return new ResponseEntity<>(consultationService.createConsultation(consultationDto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
    public ResponseEntity<ConsultationDto> getConsultationById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(consultationService.getConsultationById(id));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
    public ResponseEntity<ConsultationDto> updateConsultation(@PathVariable(name = "id") Long id,
            @RequestBody ConsultationDto consultationDto) {
        return ResponseEntity.ok(consultationService.updateConsultation(id, consultationDto));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteConsultation(@PathVariable(name = "id") Long id) {
        consultationService.deleteConsultation(id);
        return ResponseEntity.ok("Consultation deleted successfully");
    }

    @GetMapping("/{id}/prescriptions")
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
    public ResponseEntity<List<PrescriptionDto>> getConsultationPrescriptions(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(prescriptionService.getPrescriptionsByConsultationId(id));
    }
}
