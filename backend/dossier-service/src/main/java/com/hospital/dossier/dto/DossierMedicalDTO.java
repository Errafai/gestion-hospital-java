package com.hospital.dossier.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class DossierMedicalDTO {
    
    private Long id;
    
    @NotNull(message = "Patient ID is required")
    private Long patientId;
    
    private String numeroDossier;
    private String antecedentsMedicaux;
    private String antecedentsChirurgicaux;
    private String antecedentsFamiliaux;

    public DossierMedicalDTO() {
    }

    public DossierMedicalDTO(Long id, Long patientId, String numeroDossier, String antecedentsMedicaux, String antecedentsChirurgicaux, String antecedentsFamiliaux) {
        this.id = id;
        this.patientId = patientId;
        this.numeroDossier = numeroDossier;
        this.antecedentsMedicaux = antecedentsMedicaux;
        this.antecedentsChirurgicaux = antecedentsChirurgicaux;
        this.antecedentsFamiliaux = antecedentsFamiliaux;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public String getNumeroDossier() {
        return numeroDossier;
    }

    public void setNumeroDossier(String numeroDossier) {
        this.numeroDossier = numeroDossier;
    }

    public String getAntecedentsMedicaux() {
        return antecedentsMedicaux;
    }

    public void setAntecedentsMedicaux(String antecedentsMedicaux) {
        this.antecedentsMedicaux = antecedentsMedicaux;
    }

    public String getAntecedentsChirurgicaux() {
        return antecedentsChirurgicaux;
    }

    public void setAntecedentsChirurgicaux(String antecedentsChirurgicaux) {
        this.antecedentsChirurgicaux = antecedentsChirurgicaux;
    }

    public String getAntecedentsFamiliaux() {
        return antecedentsFamiliaux;
    }

    public void setAntecedentsFamiliaux(String antecedentsFamiliaux) {
        this.antecedentsFamiliaux = antecedentsFamiliaux;
    }
}

