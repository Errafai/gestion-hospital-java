package com.hospital.controller;

import com.hospital.dto.ConsultationDto;

import com.hospital.dto.MedicalRecordDto;
import com.hospital.service.ConsultationService;

import com.hospital.service.MedicalRecordService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medical-records")
public class MedicalRecordController {

    private MedicalRecordService medicalRecordService;
    private ConsultationService consultationService;

    public MedicalRecordController(MedicalRecordService medicalRecordService,
            ConsultationService consultationService) {
        this.medicalRecordService = medicalRecordService;
        this.consultationService = consultationService;
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
    public ResponseEntity<List<MedicalRecordDto>> getAllMedicalRecords() {
        return ResponseEntity.ok(medicalRecordService.getAllMedicalRecords());
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
    public ResponseEntity<MedicalRecordDto> createMedicalRecord(@RequestBody MedicalRecordDto medicalRecordDto) {
        return new ResponseEntity<>(medicalRecordService.createMedicalRecord(medicalRecordDto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
    public ResponseEntity<MedicalRecordDto> getMedicalRecordById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(medicalRecordService.getMedicalRecordById(id));
    }

    @GetMapping("/patient/{patientId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
    public ResponseEntity<MedicalRecordDto> getMedicalRecordByPatientId(
            @PathVariable(name = "patientId") Long patientId) {
        return ResponseEntity.ok(medicalRecordService.getMedicalRecordByPatientId(patientId));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
    public ResponseEntity<MedicalRecordDto> updateMedicalRecord(@PathVariable(name = "id") Long id,
            @RequestBody MedicalRecordDto medicalRecordDto) {
        return ResponseEntity.ok(medicalRecordService.updateMedicalRecord(id, medicalRecordDto));
    }

    @GetMapping("/{id}/consultations")
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
    public ResponseEntity<List<ConsultationDto>> getMedicalRecordConsultations(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(consultationService.getConsultationsByMedicalRecordId(id));
    }

}
