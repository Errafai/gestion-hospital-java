package com.hospital.dto;

import com.hospital.entity.RendezVous;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RendezVousDTO {
    
    private Long id;
    
    @NotNull(message = "Patient ID is required")
    private Long patientId;
    
    @NotNull(message = "Médecin ID is required")
    private Long medecinId;
    
    @NotNull(message = "Date is required")
    private LocalDate dateRdv;
    
    @NotNull(message = "Heure début is required")
    private LocalTime heureDebut;
    
    @NotNull(message = "Heure fin is required")
    private LocalTime heureFin;
    
    private String motif;
    private RendezVous.StatutRendezVous statut;
    private String notes;
}

