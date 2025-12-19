package com.hospital.dossier.repository;

import com.hospital.dossier.entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
/**
 * Repository pour l'entité Document.
 */
public interface DocumentRepository extends JpaRepository<Document, Long> {
    
    /**
     * Récupère la liste des documents d'un dossier médical.
     * @param dossierMedicalId ID du dossier.
     * @return Liste des documents.
     */
    List<Document> findByDossierMedicalId(Long dossierMedicalId);
}

