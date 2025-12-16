package com.hospital.rendezvous.controller;

import com.hospital.rendezvous.dto.RendezVousDTO;
import com.hospital.rendezvous.entity.RendezVous;
import com.hospital.rendezvous.service.RendezVousService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * Contrôleur REST pour la gestion des rendez-vous médicaux.
 * Accessible via l'API Gateway sous /api/rendez-vous/**.
 */
@RestController
@RequestMapping("/rendez-vous")
@CrossOrigin(origins = "*")
public class RendezVousController {
    
    @Autowired
    private RendezVousService rendezVousService;
    
    /**
     * Récupère tous les rendez-vous existants.
     */
    @GetMapping
    public ResponseEntity<List<RendezVousDTO>> getAllRendezVous() {
        List<RendezVousDTO> rendezVous = rendezVousService.getAllRendezVous();
        return ResponseEntity.ok(rendezVous);
    }
    
    /**
     * Récupère un rendez-vous par son identifiant technique.
     */
    @GetMapping("/{id}")
    public ResponseEntity<RendezVousDTO> getRendezVousById(@PathVariable Long id) {
        RendezVousDTO rendezVous = rendezVousService.getRendezVousById(id);
        return ResponseEntity.ok(rendezVous);
    }
    
    /**
     * Récupère les rendez-vous d'un patient donné.
     */
    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<RendezVousDTO>> getRendezVousByPatient(@PathVariable Long patientId) {
        List<RendezVousDTO> rendezVous = rendezVousService.getRendezVousByPatient(patientId);
        return ResponseEntity.ok(rendezVous);
    }
    
    /**
     * Récupère les rendez-vous d'un médecin donné.
     */
    @GetMapping("/medecin/{medecinId}")
    public ResponseEntity<List<RendezVousDTO>> getRendezVousByMedecin(@PathVariable Long medecinId) {
        List<RendezVousDTO> rendezVous = rendezVousService.getRendezVousByMedecin(medecinId);
        return ResponseEntity.ok(rendezVous);
    }
    
    /**
     * Récupère les rendez-vous planifiés à une date précise.
     */
    @GetMapping("/date/{date}")
    public ResponseEntity<List<RendezVousDTO>> getRendezVousByDate(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        List<RendezVousDTO> rendezVous = rendezVousService.getRendezVousByDate(date);
        return ResponseEntity.ok(rendezVous);
    }
    
    /**
     * Crée un nouveau rendez-vous.
     * Valide la disponibilité du médecin et l'absence de conflit d'horaire.
     */
    @PostMapping
    public ResponseEntity<RendezVousDTO> createRendezVous(@Valid @RequestBody RendezVousDTO rendezVousDTO) {
        RendezVousDTO created = rendezVousService.createRendezVous(rendezVousDTO);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }
    
    /**
     * Met à jour les informations d'un rendez-vous existant.
     * Gère aussi le changement de créneau horaire (contrôle des conflits).
     */
    @PutMapping("/{id}")
    public ResponseEntity<RendezVousDTO> updateRendezVous(@PathVariable Long id, 
                                                           @Valid @RequestBody RendezVousDTO rendezVousDTO) {
        RendezVousDTO updated = rendezVousService.updateRendezVous(id, rendezVousDTO);
        return ResponseEntity.ok(updated);
    }
    
    /**
     * Met uniquement à jour le statut d'un rendez-vous (PLANIFIE, CONFIRME, ANNULE, TERMINE).
     */
    @PutMapping("/{id}/statut")
    public ResponseEntity<RendezVousDTO> updateStatut(@PathVariable Long id, 
                                                      @RequestParam RendezVous.StatutRendezVous statut) {
        RendezVousDTO updated = rendezVousService.updateStatut(id, statut);
        return ResponseEntity.ok(updated);
    }
    
    /**
     * Supprime définitivement un rendez-vous.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRendezVous(@PathVariable Long id) {
        rendezVousService.deleteRendezVous(id);
        return ResponseEntity.noContent().build();
    }
}

