package com.hospital.service;

import com.hospital.dto.LoginDto;
import com.hospital.dto.RegisterDto;
import com.hospital.dto.UserDto;

public interface AuthService {
    String login(LoginDto loginDto);

    String register(RegisterDto registerDto);

    UserDto getCurrentUser();
}
