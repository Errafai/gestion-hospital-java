package com.hospital.auth.service;

import com.hospital.auth.dto.JwtAuthenticationResponse;
import com.hospital.auth.dto.LoginRequest;
import com.hospital.auth.dto.RegisterRequest;
import com.hospital.auth.entity.User;
import com.hospital.auth.exception.BadRequestException;
import com.hospital.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
/**
 * Service gérant l'authentification et l'inscription des utilisateurs.
 * Coordonne les interactions entre le repository, l'encodeur de mot de passe et le fournisseur de tokens.
 */
public class AuthService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private com.hospital.auth.security.JwtTokenProvider tokenProvider;
    
    /**
     * Inscrit un nouvel utilisateur dans le système.
     * Vérifie l'unicité du nom d'utilisateur et de l'email.
     * @param request Les données d'inscription (nom, email, mot de passe...).
     * @return L'utilisateur créé.
     * @throws BadRequestException Si le nom d'utilisateur ou l'email existe déjà.
     */
    @Transactional
    public User register(RegisterRequest request) {
        // Vérification de l'unicité du username
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new BadRequestException("Username is already taken!");
        }
        
        // Vérification de l'unicité de l'email
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new BadRequestException("Email is already in use!");
        }
        
        // Création de l'entité User
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEmail(request.getEmail());
        user.setNom(request.getNom());
        user.setPrenom(request.getPrenom());
        user.setRole(request.getRole());
        user.setActif(true);
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        
        return userRepository.save(user);
    }
    
    /**
     * Authentifie un utilisateur et génère un token JWT.
     * @param request Les identifiants de connexion (username, password).
     * @return Une réponse contenant le token JWT et les infos de l'utilisateur.
     * @throws BadRequestException Si les identifiants sont incorrects ou l'utilisateur non trouvé.
     */
    public JwtAuthenticationResponse login(LoginRequest request) {
        // Authentification via Spring Security
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );
        
        // Mise à jour du contexte de sécurité
        SecurityContextHolder.getContext().setAuthentication(authentication);
        
        // Génération du token JWT
        String jwt = tokenProvider.generateToken(authentication);
        
        // Récupération des détails de l'utilisateur pour la réponse
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new BadRequestException("User not found"));
        
        JwtAuthenticationResponse response = new JwtAuthenticationResponse();
        response.setToken(jwt);
        response.setUsername(user.getUsername());
        response.setRole(user.getRole().name());
        
        return response;
    }
}

