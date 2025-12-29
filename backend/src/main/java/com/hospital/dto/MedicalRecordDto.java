package com.hospital.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class MedicalRecordDto {
    private Long id;
    private Long patientId;
    private String patientName;
    private String recordNumber;
    private LocalDateTime createdAt;
    @com.fasterxml.jackson.annotation.JsonProperty("medicalHistory")
    private String medicalHistory;

    @com.fasterxml.jackson.annotation.JsonProperty("surgicalHistory")
    private String surgicalHistory;

    @com.fasterxml.jackson.annotation.JsonProperty("familyHistory")
    private String familyHistory;
    private LocalDateTime updatedAt;
}
