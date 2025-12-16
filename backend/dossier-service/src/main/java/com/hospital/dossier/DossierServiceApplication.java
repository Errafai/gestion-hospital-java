package com.hospital.dossier;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class DossierServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DossierServiceApplication.class, args);
    }
}

