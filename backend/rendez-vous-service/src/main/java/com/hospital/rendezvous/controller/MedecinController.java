package com.hospital.rendezvous.controller;

import com.hospital.rendezvous.dto.MedecinDTO;
import com.hospital.rendezvous.service.MedecinService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medecins")
@CrossOrigin(origins = "*")
/**
 * Contrôleur REST pour la gestion des médecins.
 * Permet de rechercher et gérer les profils médecins.
 */
public class MedecinController {
    
    @Autowired
    private MedecinService medecinService;
    
    /**
     * Liste tous les médecins.
     * @return Liste de DTOs.
     */
    @GetMapping
    public ResponseEntity<List<MedecinDTO>> getAllMedecins() {
        List<MedecinDTO> medecins = medecinService.getAllMedecins();
        return ResponseEntity.ok(medecins);
    }
    
    /**
     * Trouve un médecin par ID.
     * @param id ID du médecin.
     * @return DTO du médecin.
     */
    @GetMapping("/{id}")
    public ResponseEntity<MedecinDTO> getMedecinById(@PathVariable Long id) {
        MedecinDTO medecin = medecinService.getMedecinById(id);
        return ResponseEntity.ok(medecin);
    }
    
    /**
     * Recherche par spécialité.
     * @param specialite Spécialité médicale.
     * @return Liste de médecins.
     */
    @GetMapping("/specialite/{specialite}")
    public ResponseEntity<List<MedecinDTO>> getMedecinsBySpecialite(@PathVariable String specialite) {
        List<MedecinDTO> medecins = medecinService.getMedecinsBySpecialite(specialite);
        return ResponseEntity.ok(medecins);
    }
    
    /**
     * Placeholder pour la disponibilité (non implémenté).
     */
    @GetMapping("/{id}/disponibilites")
    public ResponseEntity<?> getMedecinDisponibilites(@PathVariable Long id) {
        return ResponseEntity.ok("Disponibilités endpoint");
    }
    
    /**
     * Crée un nouveau profil médecin.
     * @param medecinDTO Données du médecin.
     * @return Médecin créé.
     */
    @PostMapping
    public ResponseEntity<MedecinDTO> createMedecin(@Valid @RequestBody MedecinDTO medecinDTO) {
        MedecinDTO created = medecinService.createMedecin(medecinDTO);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }
    
    /**
     * Met à jour un médecin.
     * @param id ID du médecin.
     * @param medecinDTO Nouvelles données.
     * @return Médecin mis à jour.
     */
    @PutMapping("/{id}")
    public ResponseEntity<MedecinDTO> updateMedecin(@PathVariable Long id, 
                                                    @Valid @RequestBody MedecinDTO medecinDTO) {
        MedecinDTO updated = medecinService.updateMedecin(id, medecinDTO);
        return ResponseEntity.ok(updated);
    }
    
    /**
     * Supprime un médecin.
     * @param id ID du médecin.
     * @return 204 No Content.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMedecin(@PathVariable Long id) {
        medecinService.deleteMedecin(id);
        return ResponseEntity.noContent().build();
    }
}

