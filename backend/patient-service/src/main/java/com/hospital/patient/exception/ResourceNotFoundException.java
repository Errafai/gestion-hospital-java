package com.hospital.patient.exception;

/**
 * Exception lancée lorsqu'une ressource demandée n'est pas trouvée.
 * Mappe généralement vers un statut HTTP 404 Not Found.
 */
public class ResourceNotFoundException extends RuntimeException {
    
    /**
     * Constructeur avec un message simple.
     * @param message Le message d'erreur.
     */
    public ResourceNotFoundException(String message) {
        super(message);
    }
    
    /**
     * Constructeur détaillé.
     * @param resourceName Le nom du type de ressource (ex: "Patient").
     * @param fieldName Le nom du champ utilisé pour la recherche (ex: "id").
     * @param fieldValue La valeur du champ qui n'a pas été trouvée.
     */
    public ResourceNotFoundException(String resourceName, String fieldName, Object fieldValue) {
        super(String.format("%s not found with %s : '%s'", resourceName, fieldName, fieldValue));
    }
}

