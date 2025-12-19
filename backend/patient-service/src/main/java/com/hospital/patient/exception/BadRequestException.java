package com.hospital.patient.exception;

/**
 * Exception lancée lorsque la requête du client est invalide.
 * Mappe généralement vers un statut HTTP 400 Bad Request.
 */
public class BadRequestException extends RuntimeException {
    
    /**
     * Constructeur avec message.
     * @param message Description de l'erreur.
     */
    public BadRequestException(String message) {
        super(message);
    }
    
    /**
     * Constructeur avec message et cause.
     * @param message Description de l'erreur.
     * @param cause La cause racine de l'exception.
     */
    public BadRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}

