package com.hospital.auth.exception;

/**
 * Exception personnalisée pour les erreurs de requête (Bad Request).
 * Mappe vers le statut HTTP 400.
 */
public class BadRequestException extends RuntimeException {
    
    /**
     * Constructeur avec message.
     * @param message Cause de l'erreur.
     */
    public BadRequestException(String message) {
        super(message);
    }
    
    /**
     * Constructeur avec message et cause.
     * @param message Cause de l'erreur.
     * @param cause Exception racine.
     */
    public BadRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}

