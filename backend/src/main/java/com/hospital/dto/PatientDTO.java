package com.hospital.dto;

import com.hospital.entity.Patient;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientDTO {
    
    private Long id;
    
    @NotBlank(message = "Numéro patient is required")
    private String numeroPatient;
    
    @NotBlank(message = "Nom is required")
    private String nom;
    
    @NotBlank(message = "Prénom is required")
    private String prenom;
    
    @NotNull(message = "Date de naissance is required")
    private LocalDate dateNaissance;
    
    @NotNull(message = "Sexe is required")
    private Patient.Sexe sexe;
    
    private String cin;
    private String telephone;
    private String email;
    private String adresse;
    private String ville;
    private String groupeSanguin;
    private String allergies;
}

