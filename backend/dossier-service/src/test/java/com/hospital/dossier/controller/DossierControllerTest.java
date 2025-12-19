package com.hospital.dossier.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hospital.dossier.dto.DossierMedicalDTO;
import com.hospital.dossier.service.ConsultationService;
import com.hospital.dossier.service.DossierMedicalService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(DossierMedicalController.class)
public class DossierControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DossierMedicalService dossierMedicalService;

    @MockBean
    private ConsultationService consultationService;

    @Autowired
    private ObjectMapper objectMapper;

    private DossierMedicalDTO dossierMedicalDTO;

    @BeforeEach
    void setUp() {
        dossierMedicalDTO = new DossierMedicalDTO();
        dossierMedicalDTO.setId(1L);
        dossierMedicalDTO.setPatientId(1L);
        dossierMedicalDTO.setNumeroDossier("D001");
    }

    @Test
    void shouldCreateDossier() throws Exception {
        when(dossierMedicalService.createDossier(any(DossierMedicalDTO.class))).thenReturn(dossierMedicalDTO);

        mockMvc.perform(post("/dossiers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dossierMedicalDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.numeroDossier").value("D001"));
    }

    @Test
    void shouldGetDossierByPatientId() throws Exception {
        when(dossierMedicalService.getDossierByPatient(1L)).thenReturn(dossierMedicalDTO);

        mockMvc.perform(get("/dossiers/patient/{patientId}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.patientId").value(1L));
    }
}
