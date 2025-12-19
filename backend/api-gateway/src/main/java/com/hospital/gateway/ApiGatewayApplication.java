package com.hospital.gateway;

/**
 * Main entry point for the API Gateway service.
 * <p>
 * This Spring Boot application aggregates and routes requests to the various
 * microservices (e.g., auth-service, rendez-vous-service, dossier-service).
 * It uses Spring Cloud Gateway (or similar) to provide a single entry point.
 * </p>
 */
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiGatewayApplication {

    /**
     * Starts the Spring Boot application.
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }
}

