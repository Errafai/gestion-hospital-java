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

@RestController
@RequestMapping("/rendez-vous")
@CrossOrigin(origins = "*")
/**
 * Contrôleur REST pour la gestion des rendez-vous.
 * Permet de planifier, modifier, annuler et consulter les rendez-vous.
 */
public class RendezVousController {
    
    @Autowired
    private RendezVousService rendezVousService;
    
    /**
     * Liste tous les rendez-vous (Admin).
     * @return Liste de DTOs.
     */
    @GetMapping
    public ResponseEntity<List<RendezVousDTO>> getAllRendezVous() {
        List<RendezVousDTO> rendezVous = rendezVousService.getAllRendezVous();
        return ResponseEntity.ok(rendezVous);
    }
    
    /**
     * Détails d'un rendez-vous.
     * @param id ID du rendez-vous.
     * @return DTO du rendez-vous.
     */
    @GetMapping("/{id}")
    public ResponseEntity<RendezVousDTO> getRendezVousById(@PathVariable Long id) {
        RendezVousDTO rendezVous = rendezVousService.getRendezVousById(id);
        return ResponseEntity.ok(rendezVous);
    }
    
    /**
     * Historique patient.
     * @param patientId ID du patient.
     * @return Liste des rendez-vous.
     */
    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<RendezVousDTO>> getRendezVousByPatient(@PathVariable Long patientId) {
        List<RendezVousDTO> rendezVous = rendezVousService.getRendezVousByPatient(patientId);
        return ResponseEntity.ok(rendezVous);
    }
    
    /**
     * Agenda médecin.
     * @param medecinId ID du médecin.
     * @return Liste des rendez-vous.
     */
    @GetMapping("/medecin/{medecinId}")
    public ResponseEntity<List<RendezVousDTO>> getRendezVousByMedecin(@PathVariable Long medecinId) {
        List<RendezVousDTO> rendezVous = rendezVousService.getRendezVousByMedecin(medecinId);
        return ResponseEntity.ok(rendezVous);
    }
    
    /**
     * Rendez-vous par date.
     * @param date Date au format ISO.
     * @return Liste des rendez-vous.
     */
    @GetMapping("/date/{date}")
    public ResponseEntity<List<RendezVousDTO>> getRendezVousByDate(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        List<RendezVousDTO> rendezVous = rendezVousService.getRendezVousByDate(date);
        return ResponseEntity.ok(rendezVous);
    }
    
    /**
     * Planifier un rendez-vous.
     * @param rendezVousDTO Données du rendez-vous.
     * @return Rendez-vous créé (201 Created).
     */
    @PostMapping
    public ResponseEntity<RendezVousDTO> createRendezVous(@Valid @RequestBody RendezVousDTO rendezVousDTO) {
        RendezVousDTO created = rendezVousService.createRendezVous(rendezVousDTO);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }
    
    /**
     * Modifier un rendez-vous.
     * @param id ID du rendez-vous.
     * @param rendezVousDTO Nouvelles données.
     * @return Rendez-vous mis à jour.
     */
    @PutMapping("/{id}")
    public ResponseEntity<RendezVousDTO> updateRendezVous(@PathVariable Long id, 
                                                           @Valid @RequestBody RendezVousDTO rendezVousDTO) {
        RendezVousDTO updated = rendezVousService.updateRendezVous(id, rendezVousDTO);
        return ResponseEntity.ok(updated);
    }
    
    /**
     * Changer le statut (ex: Annuler).
     * @param id ID du rendez-vous.
     * @param statut Nouveau statut.
     * @return Rendez-vous mis à jour.
     */
    @PutMapping("/{id}/statut")
    public ResponseEntity<RendezVousDTO> updateStatut(@PathVariable Long id, 
                                                      @RequestParam RendezVous.StatutRendezVous statut) {
        RendezVousDTO updated = rendezVousService.updateStatut(id, statut);
        return ResponseEntity.ok(updated);
    }
    
    /**
     * Supprimer un rendez-vous.
     * @param id ID du rendez-vous.
     * @return 204 No Content.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRendezVous(@PathVariable Long id) {
        rendezVousService.deleteRendezVous(id);
        return ResponseEntity.noContent().build();
    }
}

