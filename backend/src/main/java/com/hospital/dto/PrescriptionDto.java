package com.hospital.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class PrescriptionDto {
    private Long id;
    private Long consultationId;
    private String doctorName;
    private LocalDateTime consultationDate;
    private String medication;
    private String dosage;
    private String frequency;
    private String duration;
    private String instructions;
    private Long patientId;
    private String patientName;
    private String recordNumber;
    private LocalDateTime createdAt;
}
