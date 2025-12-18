package com.hospital.auth.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * Composant responsable de la génération et de la validation des tokens JWT.
 * Utilisé par le service d'authentification et le filtre de sécurité.
 */
@Component
public class JwtTokenProvider {
    
    /**
     * Clé secrète utilisée pour signer les tokens JWT.
     * Valeur fournie dans le fichier de configuration (application.yml).
     */
    @Value("${jwt.secret}")
    private String jwtSecret;
    
    /**
     * Durée de validité du token (en millisecondes).
     */
    @Value("${jwt.expiration}")
    private long jwtExpirationMs;
    
    /**
     * Construit la clé de signature à partir de la chaîne secrète.
     */
    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
    }
    
    /**
     * Génère un token JWT à partir d'une authentification réussie.
     * Le username de l'utilisateur est stocké dans le "subject" du token.
     */
    public String generateToken(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationMs);
        
        return Jwts.builder()
                .subject(userDetails.getUsername())
                .issuedAt(now)
                .expiration(expiryDate)
                .signWith(getSigningKey())
                .compact();
    }
    
    /**
     * Extrait le nom d'utilisateur (subject) à partir d'un token JWT valide.
     */
    public String getUsernameFromToken(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
        
        return claims.getSubject();
    }
    
    /**
     * Vérifie la validité d'un token JWT (signature, format, expiration).
     */
    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith(getSigningKey())
                    .build()
                    .parseSignedClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
}

