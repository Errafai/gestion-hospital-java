package com.hospital.service.impl;

import com.hospital.dto.PrescriptionDto;
import com.hospital.entity.Consultation;
import com.hospital.entity.Prescription;
import com.hospital.entity.Patient;
import com.hospital.exception.ResourceNotFoundException;
import com.hospital.repository.ConsultationRepository;
import com.hospital.repository.PrescriptionRepository;
import com.hospital.service.PrescriptionService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PrescriptionServiceImpl implements PrescriptionService {

    private PrescriptionRepository prescriptionRepository;
    private ConsultationRepository consultationRepository;
    private ModelMapper modelMapper;

    public PrescriptionServiceImpl(PrescriptionRepository prescriptionRepository,
            ConsultationRepository consultationRepository,
            ModelMapper modelMapper) {
        this.prescriptionRepository = prescriptionRepository;
        this.consultationRepository = consultationRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public PrescriptionDto createPrescription(PrescriptionDto prescriptionDto) {
        Consultation consultation = consultationRepository.findById(prescriptionDto.getConsultationId())
                .orElseThrow(
                        () -> new ResourceNotFoundException("Consultation", "id", prescriptionDto.getConsultationId()));

        Prescription prescription = modelMapper.map(prescriptionDto, Prescription.class);
        prescription.setConsultation(consultation);

        Prescription saved = prescriptionRepository.save(prescription);
        return modelMapper.map(saved, PrescriptionDto.class);
    }

    @Override
    public List<PrescriptionDto> getAllPrescriptions() {
        return prescriptionRepository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    private PrescriptionDto mapToDto(Prescription prescription) {
        PrescriptionDto dto = modelMapper.map(prescription, PrescriptionDto.class);
        Consultation consultation = prescription.getConsultation();
        if (consultation != null) {
            dto.setConsultationDate(consultation.getConsultationDate());

            // Map doctor info
            if (consultation.getDoctor() != null && consultation.getDoctor().getUser() != null) {
                dto.setDoctorName("Dr. " + consultation.getDoctor().getUser().getFirstName() + " "
                        + consultation.getDoctor().getUser().getLastName());
            }

            // Map patient info via medical record
            if (consultation.getMedicalRecord() != null && consultation.getMedicalRecord().getPatient() != null) {
                Patient patient = consultation.getMedicalRecord().getPatient();
                dto.setPatientId(patient.getId());
                dto.setPatientName(patient.getFirstName() + " " + patient.getLastName());
                dto.setRecordNumber(consultation.getMedicalRecord().getRecordNumber());
            }
        }
        return dto;
    }

    @Override
    public PrescriptionDto getPrescriptionById(Long id) {
        Prescription prescription = prescriptionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Prescription", "id", id));
        return modelMapper.map(prescription, PrescriptionDto.class);
    }

    @Override
    public List<PrescriptionDto> getPrescriptionsByConsultationId(Long consultationId) {
        return prescriptionRepository.findByConsultationId(consultationId).stream()
                .map(p -> modelMapper.map(p, PrescriptionDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public PrescriptionDto updatePrescription(Long id, PrescriptionDto prescriptionDto) {
        Prescription prescription = prescriptionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Prescription", "id", id));

        prescription.setMedication(prescriptionDto.getMedication());
        prescription.setDosage(prescriptionDto.getDosage());
        prescription.setFrequency(prescriptionDto.getFrequency());
        prescription.setDuration(prescriptionDto.getDuration());
        prescription.setInstructions(prescriptionDto.getInstructions());

        Prescription updated = prescriptionRepository.save(prescription);
        return modelMapper.map(updated, PrescriptionDto.class);
    }

    @Override
    public void deletePrescription(Long id) {
        Prescription prescription = prescriptionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Prescription", "id", id));
        prescriptionRepository.delete(prescription);
    }
}
