package com.hospital.repository;

import com.hospital.entity.Consultation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsultationRepository extends JpaRepository<Consultation, Long> {
    List<Consultation> findByMedicalRecordId(Long medicalRecordId);

    List<Consultation> findByDoctorId(Long doctorId);
}
