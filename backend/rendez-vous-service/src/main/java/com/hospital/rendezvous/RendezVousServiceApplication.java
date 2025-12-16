package com.hospital.rendezvous;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class RendezVousServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(RendezVousServiceApplication.class, args);
    }
}

