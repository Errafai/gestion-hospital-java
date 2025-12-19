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

@RestController
@RequestMapping("/patients")
@CrossOrigin(origins = "*")
/**
 * Contrôleur REST pour la gestion des patients.
 * Expose les endpoints pour les opérations CRUD et la recherche.
 */
public class PatientController {
    
    @Autowired
    private PatientService patientService;
    
    /**
     * Récupère la liste des patients avec pagination optionnelle.
     * @param page Numéro de la page (0 par défaut).
     * @param size Taille de la page (10 par défaut).
     * @return Une liste ou une page de patients.
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
     * Récupère un patient par son ID.
     * @param id L'identifiant du patient.
     * @return Le patient trouvé ou 404 Not Found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<PatientDTO> getPatientById(@PathVariable Long id) {
        PatientDTO patient = patientService.getPatientById(id);
        return ResponseEntity.ok(patient);
    }
    
    /**
     * Récupère un patient par son numéro unique.
     * @param numero Le numéro du patient.
     * @return Le patient trouvé ou 404 Not Found.
     */
    @GetMapping("/numero/{numero}")
    public ResponseEntity<PatientDTO> getPatientByNumero(@PathVariable String numero) {
        PatientDTO patient = patientService.getPatientByNumero(numero);
        return ResponseEntity.ok(patient);
    }
    
    /**
     * Recherche des patients par mots-clés.
     * @param q Le terme de recherche.
     * @return Une liste de patients correspondants.
     */
    @GetMapping("/search")
    public ResponseEntity<List<PatientDTO>> searchPatients(@RequestParam String q) {
        List<PatientDTO> patients = patientService.searchPatients(q);
        return ResponseEntity.ok(patients);
    }
    
    /**
     * Crée un nouveau patient.
     * @param patientDTO Les données du patient.
     * @return Le patient créé avec le statut 201 Created.
     */
    @PostMapping
    public ResponseEntity<PatientDTO> createPatient(@Valid @RequestBody PatientDTO patientDTO) {
        PatientDTO created = patientService.createPatient(patientDTO);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }
    
    /**
     * Met à jour un patient existant.
     * @param id L'identifiant du patient.
     * @param patientDTO Les nouvelles données.
     * @return Le patient mis à jour.
     */
    @PutMapping("/{id}")
    public ResponseEntity<PatientDTO> updatePatient(@PathVariable Long id, 
                                                   @Valid @RequestBody PatientDTO patientDTO) {
        PatientDTO updated = patientService.updatePatient(id, patientDTO);
        return ResponseEntity.ok(updated);
    }
    
    /**
     * Supprime un patient.
     * @param id L'identifiant du patient.
     * @return 204 No Content en cas de succès.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
        patientService.deletePatient(id);
        return ResponseEntity.noContent().build();
    }
}

