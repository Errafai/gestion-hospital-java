package com.hospital.service.impl;

import com.hospital.dto.MedicalRecordDto;
import com.hospital.entity.MedicalRecord;
import com.hospital.entity.Patient;
import com.hospital.exception.ResourceNotFoundException;
import com.hospital.repository.MedicalRecordRepository;
import com.hospital.repository.PatientRepository;
import com.hospital.service.MedicalRecordService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MedicalRecordServiceImpl implements MedicalRecordService {

    private MedicalRecordRepository medicalRecordRepository;
    private PatientRepository patientRepository;
    private ModelMapper modelMapper;

    public MedicalRecordServiceImpl(MedicalRecordRepository medicalRecordRepository,
            PatientRepository patientRepository,
            ModelMapper modelMapper) {
        this.medicalRecordRepository = medicalRecordRepository;
        this.patientRepository = patientRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public MedicalRecordDto createMedicalRecord(MedicalRecordDto medicalRecordDto) {
        Patient patient = patientRepository.findById(medicalRecordDto.getPatientId())
                .orElseThrow(() -> new ResourceNotFoundException("Patient", "id", medicalRecordDto.getPatientId()));

        MedicalRecord medicalRecord = modelMapper.map(medicalRecordDto, MedicalRecord.class);
        medicalRecord.setPatient(patient);

        MedicalRecord saved = medicalRecordRepository.save(medicalRecord);
        return modelMapper.map(saved, MedicalRecordDto.class);
    }

    @Override
    public List<MedicalRecordDto> getAllMedicalRecords() {
        return medicalRecordRepository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    private MedicalRecordDto mapToDto(MedicalRecord medicalRecord) {
        MedicalRecordDto dto = modelMapper.map(medicalRecord, MedicalRecordDto.class);
        if (medicalRecord.getPatient() != null) {
            dto.setPatientName(
                    medicalRecord.getPatient().getFirstName() + " " + medicalRecord.getPatient().getLastName());
        }
        return dto;
    }

    @Override
    public MedicalRecordDto getMedicalRecordById(Long id) {
        MedicalRecord medicalRecord = medicalRecordRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("MedicalRecord", "id", id));
        return modelMapper.map(medicalRecord, MedicalRecordDto.class);
    }

    @Override
    public MedicalRecordDto getMedicalRecordByPatientId(Long patientId) {
        MedicalRecord medicalRecord = medicalRecordRepository.findByPatientId(patientId)
                .orElseThrow(() -> new ResourceNotFoundException("MedicalRecord", "patientId", patientId));
        return modelMapper.map(medicalRecord, MedicalRecordDto.class);
    }

    @Override
    public MedicalRecordDto updateMedicalRecord(Long id, MedicalRecordDto medicalRecordDto) {
        MedicalRecord medicalRecord = medicalRecordRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("MedicalRecord", "id", id));

        medicalRecord.setMedicalHistory(medicalRecordDto.getMedicalHistory());
        medicalRecord.setSurgicalHistory(medicalRecordDto.getSurgicalHistory());
        medicalRecord.setFamilyHistory(medicalRecordDto.getFamilyHistory());

        MedicalRecord updated = medicalRecordRepository.save(medicalRecord);
        return modelMapper.map(updated, MedicalRecordDto.class);
    }
}
