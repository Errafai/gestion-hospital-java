package com.hospital.patient.controller;

import com.hospital.patient.dto.PatientDTO;
import com.hospital.patient.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Contrôleur REST pour la gestion des patients.
 * Tous les endpoints sont exposés via l'API Gateway sous /api/patients/**.
 */
@RestController
@RequestMapping("/patients")
@CrossOrigin(origins = "*")
public class PatientController {
    
    @Autowired
    private PatientService patientService;
    
    /**
     * Récupère la liste des patients.
     * - Si les paramètres page/size sont fournis (>=0 et >0), renvoie une page.
     * - Sinon, renvoie la liste complète.
     */
    @GetMapping
    public ResponseEntity<?> getAllPatients(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        if (page >= 0 && size > 0) {
            Pageable pageable = PageRequest.of(page, size);
            Page<PatientDTO> patients = patientService.getAllPatients(pageable);
            return ResponseEntity.ok(patients);
        } else {
            List<PatientDTO> patients = patientService.getAllPatients();
            return ResponseEntity.ok(patients);
        }
    }
    
    /**
     * Récupère les détails d'un patient par son identifiant technique.
     */
    @GetMapping("/{id}")
    public ResponseEntity<PatientDTO> getPatientById(@PathVariable Long id) {
        PatientDTO patient = patientService.getPatientById(id);
        return ResponseEntity.ok(patient);
    }
    
    /**
     * Récupère un patient à partir de son numéro de patient (référence métier).
     */
    @GetMapping("/numero/{numero}")
    public ResponseEntity<PatientDTO> getPatientByNumero(@PathVariable String numero) {
        PatientDTO patient = patientService.getPatientByNumero(numero);
        return ResponseEntity.ok(patient);
    }
    
    /**
     * Recherche des patients par mot-clé (nom, prénom, etc.).
     */
    @GetMapping("/search")
    public ResponseEntity<List<PatientDTO>> searchPatients(@RequestParam String q) {
        List<PatientDTO> patients = patientService.searchPatients(q);
        return ResponseEntity.ok(patients);
    }
    
    /**
     * Crée un nouveau patient.
     * - Vérifie l'unicité du numéro de patient et du CIN (si renseigné).
     */
    @PostMapping
    public ResponseEntity<PatientDTO> createPatient(@Valid @RequestBody PatientDTO patientDTO) {
        PatientDTO created = patientService.createPatient(patientDTO);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }
    
    /**
     * Met à jour les informations d'un patient existant.
     */
    @PutMapping("/{id}")
    public ResponseEntity<PatientDTO> updatePatient(@PathVariable Long id, 
                                                   @Valid @RequestBody PatientDTO patientDTO) {
        PatientDTO updated = patientService.updatePatient(id, patientDTO);
        return ResponseEntity.ok(updated);
    }
    
    /**
     * Supprime définitivement un patient de la base de données.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
        patientService.deletePatient(id);
        return ResponseEntity.noContent().build();
    }
}

