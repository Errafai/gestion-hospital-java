package com.hospital.rendezvous.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hospital.rendezvous.dto.RendezVousDTO;
import com.hospital.rendezvous.service.RendezVousService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(RendezVousController.class)
public class RendezVousControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RendezVousService rendezVousService;

    @Autowired
    private ObjectMapper objectMapper;

    private RendezVousDTO rendezVousDTO;

    @BeforeEach
    void setUp() {
        rendezVousDTO = new RendezVousDTO();
        rendezVousDTO.setId(1L);
        rendezVousDTO.setPatientId(1L);
        rendezVousDTO.setMedecinId(1L);
        rendezVousDTO.setDateRdv(java.time.LocalDate.now().plusDays(1));
        rendezVousDTO.setHeureDebut(java.time.LocalTime.of(10, 0));
        rendezVousDTO.setHeureFin(java.time.LocalTime.of(10, 30));
    }

    @Test
    void shouldCreateRendezVous() throws Exception {
        when(rendezVousService.createRendezVous(any(RendezVousDTO.class))).thenReturn(rendezVousDTO);

        mockMvc.perform(post("/rendez-vous")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(rendezVousDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L));
    }

    @Test
    void shouldGetRendezVousByPatientId() throws Exception {
        when(rendezVousService.getRendezVousByPatient(1L)).thenReturn(Collections.singletonList(rendezVousDTO));

        mockMvc.perform(get("/rendez-vous/patient/{patientId}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L));
    }
}
