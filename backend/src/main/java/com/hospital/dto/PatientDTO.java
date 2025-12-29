package com.hospital.dto;

import com.hospital.entity.Gender;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class PatientDto {
    private Long id;
    private String patientNumber;

    @com.fasterxml.jackson.annotation.JsonProperty("lastName")
    private String lastName;

    @com.fasterxml.jackson.annotation.JsonProperty("firstName")
    private String firstName;

    @com.fasterxml.jackson.annotation.JsonProperty("dateOfBirth")
    private LocalDate birthDate;

    @com.fasterxml.jackson.annotation.JsonProperty("gender")
    private Gender gender;

    private String cin;

    @com.fasterxml.jackson.annotation.JsonProperty("phone")
    private String phone;

    private String email;

    @com.fasterxml.jackson.annotation.JsonProperty("address")
    private String address;

    @com.fasterxml.jackson.annotation.JsonProperty("city")
    private String city;

    @com.fasterxml.jackson.annotation.JsonProperty("bloodGroup")
    private String bloodGroup;

    private String allergies;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
