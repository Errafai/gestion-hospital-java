package com.hospital.rendezvous.service;

import com.hospital.rendezvous.dto.RendezVousDTO;
import com.hospital.rendezvous.entity.Medecin;
import com.hospital.rendezvous.entity.RendezVous;
import com.hospital.rendezvous.exception.BadRequestException;
import com.hospital.rendezvous.repository.MedecinRepository;
import com.hospital.rendezvous.repository.RendezVousRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RendezVousServiceTest {

    @Mock
    private RendezVousRepository rendezVousRepository;

    @Mock
    private MedecinRepository medecinRepository;

    @InjectMocks
    private RendezVousService rendezVousService;

    private RendezVousDTO rendezVousDTO;
    private Medecin medecin;
    private RendezVous rendezVous;

    @BeforeEach
    void setUp() {
        medecin = new Medecin();
        medecin.setId(1L);
        medecin.setDisponible(true);

        rendezVousDTO = new RendezVousDTO();
        rendezVousDTO.setMedecinId(1L);
        rendezVousDTO.setPatientId(1L);
        rendezVousDTO.setDateRdv(LocalDate.now().plusDays(1));
        rendezVousDTO.setHeureDebut(LocalTime.of(10, 0));
        rendezVousDTO.setHeureFin(LocalTime.of(10, 30));

        rendezVous = new RendezVous();
        rendezVous.setId(1L);
        rendezVous.setMedecinId(1L);
        rendezVous.setStatut(RendezVous.StatutRendezVous.PLANIFIE);
    }

    @Test
    void shouldCreateRendezVousSuccessfully() {
        when(medecinRepository.findById(1L)).thenReturn(Optional.of(medecin));
        when(rendezVousRepository.findConflictingRendezVous(any(), any(), any(), any())).thenReturn(Collections.emptyList());
        when(rendezVousRepository.save(any(RendezVous.class))).thenReturn(rendezVous);

        RendezVousDTO result = rendezVousService.createRendezVous(rendezVousDTO);

        assertNotNull(result);
        verify(rendezVousRepository, times(1)).save(any(RendezVous.class));
    }

    @Test
    void shouldThrowExceptionWhenMedecinNotAvailable() {
        medecin.setDisponible(false);
        when(medecinRepository.findById(1L)).thenReturn(Optional.of(medecin));

        assertThrows(BadRequestException.class, () -> {
            rendezVousService.createRendezVous(rendezVousDTO);
        });
        verify(rendezVousRepository, never()).save(any(RendezVous.class));
    }
}
