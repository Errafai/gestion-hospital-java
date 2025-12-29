package com.hospital.service.impl;

import com.hospital.dto.PatientDto;
import com.hospital.entity.Patient;
import com.hospital.exception.ResourceNotFoundException;
import com.hospital.repository.PatientRepository;
import com.hospital.service.PatientService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PatientServiceImpl implements PatientService {

    private PatientRepository patientRepository;
    private ModelMapper modelMapper;

    public PatientServiceImpl(PatientRepository patientRepository, ModelMapper modelMapper) {
        this.patientRepository = patientRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public PatientDto createPatient(PatientDto patientDto) {
        Patient patient = modelMapper.map(patientDto, Patient.class);
        Patient savedPatient = patientRepository.save(patient);
        return modelMapper.map(savedPatient, PatientDto.class);
    }

    @Override
    public PatientDto getPatientById(Long id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient", "id", id));
        return modelMapper.map(patient, PatientDto.class);
    }

    @Override
    public PatientDto getPatientByPatientNumber(String patientNumber) {
        Patient patient = patientRepository.findByPatientNumber(patientNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Patient", "patientNumber", patientNumber));
        return modelMapper.map(patient, PatientDto.class);
    }

    @Override
    public PatientDto updatePatient(Long id, PatientDto patientDto) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient", "id", id));

        patient.setLastName(patientDto.getLastName());
        patient.setFirstName(patientDto.getFirstName());
        patient.setAddress(patientDto.getAddress());
        patient.setPhone(patientDto.getPhone());
        patient.setEmail(patientDto.getEmail());
        patient.setGender(patientDto.getGender());
        patient.setBirthDate(patientDto.getBirthDate());
        patient.setCin(patientDto.getCin());
        patient.setCity(patientDto.getCity());
        patient.setBloodGroup(patientDto.getBloodGroup());
        patient.setAllergies(patientDto.getAllergies());

        Patient updatedPatient = patientRepository.save(patient);
        return modelMapper.map(updatedPatient, PatientDto.class);
    }

    @Override
    public void deletePatient(Long id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient", "id", id));
        patientRepository.delete(patient);
    }

    @Override
    public Page<PatientDto> getAllPatients(Pageable pageable) {
        Page<Patient> patients = patientRepository.findAll(pageable);
        return patients.map(patient -> modelMapper.map(patient, PatientDto.class));
    }

    @Override
    public Page<PatientDto> searchPatients(String query, Pageable pageable) {
        Page<Patient> patients = patientRepository.search(query, pageable);
        return patients.map(patient -> modelMapper.map(patient, PatientDto.class));
    }
}
