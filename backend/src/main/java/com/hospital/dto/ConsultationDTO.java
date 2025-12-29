package com.hospital.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ConsultationDto {
    private Long id;
    private Long medicalRecordId;
    private Long doctorId;
    private String doctorName;
    private Long patientId;
    private String patientName;
    private Long appointmentId;
    private LocalDateTime consultationDate;
    private String symptoms;
    private String diagnosis;
    private String treatment;
    private String observations;
    private LocalDateTime createdAt;
}
