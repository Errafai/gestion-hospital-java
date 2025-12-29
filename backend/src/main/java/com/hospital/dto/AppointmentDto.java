package com.hospital.dto;

import com.hospital.entity.AppointmentStatus;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
public class AppointmentDto {
    private Long id;
    private Long patientId;
    private String patientLastName;
    private String patientFirstName;
    @com.fasterxml.jackson.annotation.JsonProperty("doctorId")
    private Long doctorId;

    private String doctorName;

    @com.fasterxml.jackson.annotation.JsonProperty("appointmentDate")
    private LocalDate appointmentDate;

    @com.fasterxml.jackson.annotation.JsonProperty("startTime")
    private LocalTime startTime;

    @com.fasterxml.jackson.annotation.JsonProperty("endTime")
    private LocalTime endTime;

    @com.fasterxml.jackson.annotation.JsonProperty("reason")
    private String reason;

    @com.fasterxml.jackson.annotation.JsonProperty("status")
    private AppointmentStatus status;

    private String notes;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
