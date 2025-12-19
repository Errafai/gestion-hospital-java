package com.hospital.dossier.service;

import com.hospital.dossier.dto.DossierMedicalDTO;
import com.hospital.dossier.entity.DossierMedical;
import com.hospital.dossier.repository.DossierMedicalRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DossierMedicalServiceTest {

    @Mock
    private DossierMedicalRepository dossierMedicalRepository;

    @InjectMocks
    private DossierMedicalService dossierMedicalService;

    private DossierMedical dossierMedical;
    private DossierMedicalDTO dossierMedicalDTO;

    @BeforeEach
    void setUp() {
        dossierMedical = new DossierMedical();
        dossierMedical.setId(1L);
        dossierMedical.setPatientId(1L);
        dossierMedical.setNumeroDossier("D001");

        dossierMedicalDTO = new DossierMedicalDTO();
        dossierMedicalDTO.setId(1L);
        dossierMedicalDTO.setPatientId(1L);
        dossierMedicalDTO.setNumeroDossier("D001");
    }

    @Test
    void shouldCreateDossier() {
        when(dossierMedicalRepository.findByPatientId(1L)).thenReturn(Optional.empty());
        when(dossierMedicalRepository.save(any(DossierMedical.class))).thenReturn(dossierMedical);

        DossierMedicalDTO result = dossierMedicalService.createDossier(dossierMedicalDTO);

        assertNotNull(result);
        assertEquals("D001", result.getNumeroDossier());
    }

    @Test
    void shouldGetDossierByPatientId() {
        when(dossierMedicalRepository.findByPatientId(1L)).thenReturn(Optional.of(dossierMedical));

        DossierMedicalDTO result = dossierMedicalService.getDossierByPatient(1L);

        assertNotNull(result);
        assertEquals(1L, result.getPatientId());
    }
}
