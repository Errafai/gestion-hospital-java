package com.hospital.auth.repository;

import com.hospital.auth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
/**
 * Repository pour l'entité User.
 * Gère les opérations de persistance pour les utilisateurs.
 */
public interface UserRepository extends JpaRepository<User, Long> {
    
    /**
     * Trouve un utilisateur par son nom d'utilisateur.
     * @param username Le nom d'utilisateur.
     * @return Un Optional contenant l'utilisateur s'il existe.
     */
    Optional<User> findByUsername(String username);
    
    /**
     * Trouve un utilisateur par son email.
     * @param email L'adresse email.
     * @return Un Optional contenant l'utilisateur s'il existe.
     */
    Optional<User> findByEmail(String email);
    
    /**
     * Vérifie si un nom d'utilisateur existe déjà.
     * @param username Le nom d'utilisateur à vérifier.
     * @return true si existe, sinon false.
     */
    Boolean existsByUsername(String username);
    
    /**
     * Vérifie si un email existe déjà.
     * @param email L'email à vérifier.
     * @return true si existe, sinon false.
     */
    Boolean existsByEmail(String email);
}

