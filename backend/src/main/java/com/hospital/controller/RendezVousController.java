package com.hospital.controller;

import com.hospital.dto.RendezVousDTO;
import com.hospital.entity.RendezVous;
import com.hospital.service.RendezVousService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/rendez-vous")
@CrossOrigin(origins = "*")
public class RendezVousController {
    
    @Autowired
    private RendezVousService rendezVousService;
    
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'MEDECIN', 'RECEPTIONNISTE')")
    public ResponseEntity<List<RendezVousDTO>> getAllRendezVous() {
        List<RendezVousDTO> rendezVous = rendezVousService.getAllRendezVous();
        return ResponseEntity.ok(rendezVous);
    }
    
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MEDECIN', 'RECEPTIONNISTE')")
    public ResponseEntity<RendezVousDTO> getRendezVousById(@PathVariable Long id) {
        RendezVousDTO rendezVous = rendezVousService.getRendezVousById(id);
        return ResponseEntity.ok(rendezVous);
    }
    
    @GetMapping("/patient/{patientId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MEDECIN', 'RECEPTIONNISTE')")
    public ResponseEntity<List<RendezVousDTO>> getRendezVousByPatient(@PathVariable Long patientId) {
        List<RendezVousDTO> rendezVous = rendezVousService.getRendezVousByPatient(patientId);
        return ResponseEntity.ok(rendezVous);
    }
    
    @GetMapping("/medecin/{medecinId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MEDECIN', 'RECEPTIONNISTE')")
    public ResponseEntity<List<RendezVousDTO>> getRendezVousByMedecin(@PathVariable Long medecinId) {
        List<RendezVousDTO> rendezVous = rendezVousService.getRendezVousByMedecin(medecinId);
        return ResponseEntity.ok(rendezVous);
    }
    
    @GetMapping("/date/{date}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MEDECIN', 'RECEPTIONNISTE')")
    public ResponseEntity<List<RendezVousDTO>> getRendezVousByDate(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        List<RendezVousDTO> rendezVous = rendezVousService.getRendezVousByDate(date);
        return ResponseEntity.ok(rendezVous);
    }
    
    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'RECEPTIONNISTE')")
    public ResponseEntity<RendezVousDTO> createRendezVous(@Valid @RequestBody RendezVousDTO rendezVousDTO) {
        RendezVousDTO created = rendezVousService.createRendezVous(rendezVousDTO);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }
    
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'RECEPTIONNISTE')")
    public ResponseEntity<RendezVousDTO> updateRendezVous(@PathVariable Long id, 
                                                           @Valid @RequestBody RendezVousDTO rendezVousDTO) {
        RendezVousDTO updated = rendezVousService.updateRendezVous(id, rendezVousDTO);
        return ResponseEntity.ok(updated);
    }
    
    @PutMapping("/{id}/statut")
    @PreAuthorize("hasAnyRole('ADMIN', 'MEDECIN', 'RECEPTIONNISTE')")
    public ResponseEntity<RendezVousDTO> updateStatut(@PathVariable Long id, 
                                                      @RequestParam RendezVous.StatutRendezVous statut) {
        RendezVousDTO updated = rendezVousService.updateStatut(id, statut);
        return ResponseEntity.ok(updated);
    }
    
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'RECEPTIONNISTE')")
    public ResponseEntity<Void> deleteRendezVous(@PathVariable Long id) {
        rendezVousService.deleteRendezVous(id);
        return ResponseEntity.noContent().build();
    }
}

