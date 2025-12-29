package com.hospital.service;

import com.hospital.dto.PrescriptionDto;
import java.util.List;

public interface PrescriptionService {
    PrescriptionDto createPrescription(PrescriptionDto prescriptionDto);

    List<PrescriptionDto> getAllPrescriptions();

    PrescriptionDto getPrescriptionById(Long id);

    List<PrescriptionDto> getPrescriptionsByConsultationId(Long consultationId);

    PrescriptionDto updatePrescription(Long id, PrescriptionDto prescriptionDto);

    void deletePrescription(Long id);
}
