package com.hospital.dto;

import com.hospital.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class UserDto {
    private Long id;
    private String username;
    private String email;

    @com.fasterxml.jackson.annotation.JsonProperty("lastName")
    private String lastName;

    @com.fasterxml.jackson.annotation.JsonProperty("firstName")
    private String firstName;

    private Role role;
    private boolean active;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
