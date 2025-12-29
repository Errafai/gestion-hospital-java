package com.hospital.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class DoctorDto {
    private Long id;
    private UserDto user;
    @com.fasterxml.jackson.annotation.JsonProperty("licenseNumber")
    private String licenseNumber;

    @com.fasterxml.jackson.annotation.JsonProperty("specialty")
    private String specialty;

    @com.fasterxml.jackson.annotation.JsonProperty("phone")
    private String phone;

    @com.fasterxml.jackson.annotation.JsonProperty("isAvailable")
    private boolean available;
    private LocalDateTime createdAt;
}
