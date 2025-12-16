package com.hospital.controller;

import com.hospital.dto.MedecinDTO;
import com.hospital.service.MedecinService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medecins")
@CrossOrigin(origins = "*")
public class MedecinController {
    
    @Autowired
    private MedecinService medecinService;
    
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'MEDECIN', 'RECEPTIONNISTE')")
    public ResponseEntity<List<MedecinDTO>> getAllMedecins() {
        List<MedecinDTO> medecins = medecinService.getAllMedecins();
        return ResponseEntity.ok(medecins);
    }
    
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MEDECIN', 'RECEPTIONNISTE')")
    public ResponseEntity<MedecinDTO> getMedecinById(@PathVariable Long id) {
        MedecinDTO medecin = medecinService.getMedecinById(id);
        return ResponseEntity.ok(medecin);
    }
    
    @GetMapping("/specialite/{specialite}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MEDECIN', 'RECEPTIONNISTE')")
    public ResponseEntity<List<MedecinDTO>> getMedecinsBySpecialite(@PathVariable String specialite) {
        List<MedecinDTO> medecins = medecinService.getMedecinsBySpecialite(specialite);
        return ResponseEntity.ok(medecins);
    }
    
    @GetMapping("/{id}/disponibilites")
    @PreAuthorize("hasAnyRole('ADMIN', 'MEDECIN', 'RECEPTIONNISTE')")
    public ResponseEntity<?> getMedecinDisponibilites(@PathVariable Long id) {
        // TODO: Implement disponibilités logic
        return ResponseEntity.ok("Disponibilités endpoint");
    }
    
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<MedecinDTO> createMedecin(@Valid @RequestBody MedecinDTO medecinDTO) {
        MedecinDTO created = medecinService.createMedecin(medecinDTO);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }
    
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<MedecinDTO> updateMedecin(@PathVariable Long id, 
                                                    @Valid @RequestBody MedecinDTO medecinDTO) {
        MedecinDTO updated = medecinService.updateMedecin(id, medecinDTO);
        return ResponseEntity.ok(updated);
    }
    
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteMedecin(@PathVariable Long id) {
        medecinService.deleteMedecin(id);
        return ResponseEntity.noContent().build();
    }
}

