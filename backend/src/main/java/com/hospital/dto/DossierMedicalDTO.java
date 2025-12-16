package com.hospital.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DossierMedicalDTO {
    
    private Long id;
    
    @NotNull(message = "Patient ID is required")
    private Long patientId;
    
    private String numeroDossier;
    private String antecedentsMedicaux;
    private String antecedentsChirurgicaux;
    private String antecedentsFamiliaux;
}

