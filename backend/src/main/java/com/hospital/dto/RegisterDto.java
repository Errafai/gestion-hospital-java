package com.hospital.dto;

//import com.hospital.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDto {
    private String name;
    private String email;
    private String password;
    private String role; // Frontend sends 'admin'|'doctor'|'nurse'
}
