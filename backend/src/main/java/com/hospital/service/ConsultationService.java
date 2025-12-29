package com.hospital.service;

import com.hospital.dto.ConsultationDto;
import java.util.List;

public interface ConsultationService {
    ConsultationDto createConsultation(ConsultationDto consultationDto);

    List<ConsultationDto> getAllConsultations();

    ConsultationDto getConsultationById(Long id);

    List<ConsultationDto> getConsultationsByMedicalRecordId(Long medicalRecordId);

    ConsultationDto updateConsultation(Long id, ConsultationDto consultationDto);

    void deleteConsultation(Long id);
}
