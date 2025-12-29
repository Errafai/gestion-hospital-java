package com.hospital.service.impl;

import com.hospital.dto.LoginDto;
import com.hospital.dto.RegisterDto;
import com.hospital.dto.UserDto;
import com.hospital.entity.User;
import com.hospital.exception.APIException;
import com.hospital.exception.ResourceNotFoundException;
import com.hospital.repository.UserRepository;
import com.hospital.security.JwtTokenProvider;
import com.hospital.service.AuthService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private JwtTokenProvider jwtTokenProvider;
    private ModelMapper modelMapper;

    public AuthServiceImpl(AuthenticationManager authenticationManager,
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            JwtTokenProvider jwtTokenProvider,
            ModelMapper modelMapper) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
        this.modelMapper = modelMapper;
    }

    @Override
    public String login(LoginDto loginDto) {
        // Frontend sends 'email', but backend checks username/email via
        // loadUserByUsername
        // We can pass email as the principal.
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getEmail(), loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenProvider.generateToken(authentication);

        return token;
    }

    @Override
    public String register(RegisterDto registerDto) {

        // Generate username from email (part before @)
        String username = registerDto.getEmail().split("@")[0];

        // check for username exists in database
        if (userRepository.existsByUsername(username)) {
            throw new APIException(HttpStatus.BAD_REQUEST, "Username already exists!.");
        }

        // check for email exists in database
        if (userRepository.existsByEmail(registerDto.getEmail())) {
            throw new APIException(HttpStatus.BAD_REQUEST, "Email is already exists!.");
        }

        // Parse name into firstName and lastName
        String[] nameParts = registerDto.getName().trim().split("\\s+", 2);
        String firstName = nameParts[0];
        String lastName = nameParts.length > 1 ? nameParts[1] : "";

        User user = new User();
        user.setLastName(lastName);
        user.setFirstName(firstName);
        user.setUsername(username);
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        // Map roles
        switch (registerDto.getRole().toLowerCase()) {
            case "admin":
                user.setRole(com.hospital.entity.Role.ADMIN);
                break;
            case "doctor":
            case "medecin": // Keep French support just in case
                user.setRole(com.hospital.entity.Role.DOCTOR);
                break;
            case "nurse":
            case "receptionniste": // Keep French support
                user.setRole(com.hospital.entity.Role.NURSE);
                break;
            default:
                throw new APIException(HttpStatus.BAD_REQUEST, "Invalid role: " + registerDto.getRole());
        }

        user.setActive(true);

        userRepository.save(user);

        return "User registered successfully!.";
    }

    @Override
    public UserDto getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userRepository.findByUsernameOrEmail(username, username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));
        return modelMapper.map(user, UserDto.class);
    }
}
