package com.hospital.service;

import com.hospital.dto.MedicalRecordDto;
import java.util.List;

public interface MedicalRecordService {
    MedicalRecordDto createMedicalRecord(MedicalRecordDto medicalRecordDto);

    List<MedicalRecordDto> getAllMedicalRecords();

    MedicalRecordDto getMedicalRecordById(Long id);

    MedicalRecordDto getMedicalRecordByPatientId(Long patientId);

    MedicalRecordDto updateMedicalRecord(Long id, MedicalRecordDto medicalRecordDto);
}
