package com.hospital.dossier.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConsultationDTO {
    
    private Long id;
    
    @NotNull(message = "Dossier médical ID is required")
    private Long dossierMedicalId;
    
    @NotNull(message = "Médecin ID is required")
    private Long medecinId;
    
    private Long rendezVousId;
    
    @NotNull(message = "Date consultation is required")
    private LocalDateTime dateConsultation;
    
    private String symptomes;
    private String diagnostic;
    private String traitement;
    private String observations;
}

