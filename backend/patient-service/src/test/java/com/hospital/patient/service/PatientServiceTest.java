package com.hospital.patient.service;

import com.hospital.patient.dto.PatientDTO;
import com.hospital.patient.entity.Patient;
import com.hospital.patient.exception.BadRequestException;
import com.hospital.patient.exception.ResourceNotFoundException;
import com.hospital.patient.repository.PatientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PatientServiceTest {

    @Mock
    private PatientRepository patientRepository;

    @InjectMocks
    private PatientService patientService;

    private Patient patient;
    private PatientDTO patientDTO;

    @BeforeEach
    void setUp() {
        patient = new Patient();
        patient.setId(1L);
        patient.setNumeroPatient("P001");
        patient.setNom("Doe");
        patient.setPrenom("John");
        patient.setDateNaissance(LocalDate.of(1990, 1, 1));
        patient.setSexe(Patient.Sexe.M);
        patient.setCin("AB123456");
        patient.setEmail("john.doe@test.com");

        patientDTO = new PatientDTO();
        patientDTO.setId(1L);
        patientDTO.setNumeroPatient("P001");
        patientDTO.setNom("Doe");
        patientDTO.setPrenom("John");
        patientDTO.setDateNaissance(LocalDate.of(1990, 1, 1));
        patientDTO.setSexe(Patient.Sexe.M);
        patientDTO.setCin("AB123456");
        patientDTO.setEmail("john.doe@test.com");
    }

    @Test
    void shouldReturnAllPatients() {
        when(patientRepository.findAll()).thenReturn(Arrays.asList(patient));

        List<PatientDTO> result = patientService.getAllPatients();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Doe", result.get(0).getNom());
        verify(patientRepository, times(1)).findAll();
    }

    @Test
    void shouldGetPatientById() {
        when(patientRepository.findById(1L)).thenReturn(Optional.of(patient));

        PatientDTO result = patientService.getPatientById(1L);

        assertNotNull(result);
        assertEquals("P001", result.getNumeroPatient());
    }

    @Test
    void shouldThrowExceptionWhenPatientNotFound() {
        when(patientRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            patientService.getPatientById(99L);
        });
    }

    @Test
    void shouldCreatePatient() {
        when(patientRepository.existsByNumeroPatient(any())).thenReturn(false);
        when(patientRepository.existsByCin(any())).thenReturn(false);
        when(patientRepository.save(any(Patient.class))).thenReturn(patient);

        PatientDTO result = patientService.createPatient(patientDTO);

        assertNotNull(result);
        assertEquals("Doe", result.getNom());
        verify(patientRepository, times(1)).save(any(Patient.class));
    }

    @Test
    void shouldThrowExceptionWhenDuplicateNumero() {
        when(patientRepository.existsByNumeroPatient(any())).thenReturn(true);

        assertThrows(BadRequestException.class, () -> {
            patientService.createPatient(patientDTO);
        });
        verify(patientRepository, never()).save(any(Patient.class));
    }
}
