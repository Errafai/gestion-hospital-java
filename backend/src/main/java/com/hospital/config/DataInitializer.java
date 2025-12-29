package com.hospital.config;

import com.hospital.entity.Role;
import com.hospital.entity.User;
import com.hospital.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public DataInitializer(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        // Init Admin User
        if (!userRepository.existsByUsername("admin")) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setEmail("admin@hospital.com");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setLastName("Admin");
            admin.setFirstName("System");
            admin.setRole(Role.ADMIN);
            admin.setActive(true);
            userRepository.save(admin);
            System.out.println("Admin user created: admin / admin123");
        }

        // Init Medecin User
        if (!userRepository.existsByUsername("doctor1")) {
            User doc = new User();
            doc.setUsername("doctor1");
            doc.setEmail("doctor1@hospital.com");
            doc.setPassword(passwordEncoder.encode("doctor123"));
            doc.setLastName("House");
            doc.setFirstName("Gregory");
            doc.setRole(Role.DOCTOR);
            doc.setActive(true);
            userRepository.save(doc);
            System.out.println("Doctor user created: doctor1 / doctor123");
        }
    }
}
