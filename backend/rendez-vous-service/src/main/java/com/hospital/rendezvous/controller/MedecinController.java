package com.hospital.rendezvous.controller;

import com.hospital.rendezvous.dto.MedecinDTO;
import com.hospital.rendezvous.service.MedecinService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Contrôleur REST pour la gestion des médecins.
 * Accessible via l'API Gateway sous /api/medecins/**.
 */
@RestController
@RequestMapping("/medecins")
@CrossOrigin(origins = "*")
public class MedecinController {
    
    @Autowired
    private MedecinService medecinService;
    
    /**
     * Récupère la liste de tous les médecins.
     */
    @GetMapping
    public ResponseEntity<List<MedecinDTO>> getAllMedecins() {
        List<MedecinDTO> medecins = medecinService.getAllMedecins();
        return ResponseEntity.ok(medecins);
    }
    
    /**
     * Récupère un médecin par son identifiant.
     */
    @GetMapping("/{id}")
    public ResponseEntity<MedecinDTO> getMedecinById(@PathVariable Long id) {
        MedecinDTO medecin = medecinService.getMedecinById(id);
        return ResponseEntity.ok(medecin);
    }
    
    /**
     * Récupère les médecins filtrés par spécialité.
     */
    @GetMapping("/specialite/{specialite}")
    public ResponseEntity<List<MedecinDTO>> getMedecinsBySpecialite(@PathVariable String specialite) {
        List<MedecinDTO> medecins = medecinService.getMedecinsBySpecialite(specialite);
        return ResponseEntity.ok(medecins);
    }
    
    /**
     * (Placeholder) Endpoint prévu pour renvoyer les disponibilités d'un médecin.
     * À implémenter ultérieurement si nécessaire.
     */
    @GetMapping("/{id}/disponibilites")
    public ResponseEntity<?> getMedecinDisponibilites(@PathVariable Long id) {
        return ResponseEntity.ok("Disponibilités endpoint");
    }
    
    /**
     * Crée un nouveau médecin (lié à un user existant via userId).
     */
    @PostMapping
    public ResponseEntity<MedecinDTO> createMedecin(@Valid @RequestBody MedecinDTO medecinDTO) {
        MedecinDTO created = medecinService.createMedecin(medecinDTO);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }
    
    /**
     * Met à jour les informations d'un médecin.
     */
    @PutMapping("/{id}")
    public ResponseEntity<MedecinDTO> updateMedecin(@PathVariable Long id, 
                                                    @Valid @RequestBody MedecinDTO medecinDTO) {
        MedecinDTO updated = medecinService.updateMedecin(id, medecinDTO);
        return ResponseEntity.ok(updated);
    }
    
    /**
     * Supprime définitivement un médecin.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMedecin(@PathVariable Long id) {
        medecinService.deleteMedecin(id);
        return ResponseEntity.noContent().build();
    }
}

