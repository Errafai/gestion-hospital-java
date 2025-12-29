package com.hospital.service;

import com.hospital.dto.PatientDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PatientService {
    PatientDto createPatient(PatientDto patientDto);

    PatientDto getPatientById(Long id);

    PatientDto getPatientByPatientNumber(String patientNumber);

    PatientDto updatePatient(Long id, PatientDto patientDto);

    void deletePatient(Long id);

    Page<PatientDto> getAllPatients(Pageable pageable);

    Page<PatientDto> searchPatients(String query, Pageable pageable);
}
