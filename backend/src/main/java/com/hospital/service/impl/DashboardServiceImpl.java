package com.hospital.service.impl;

import com.hospital.dto.AppointmentDto;
import com.hospital.repository.MedicalRecordRepository;
import com.hospital.repository.DoctorRepository;
import com.hospital.repository.PatientRepository;
import com.hospital.repository.AppointmentRepository;
import com.hospital.service.DashboardService;
import com.hospital.service.AppointmentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DashboardServiceImpl implements DashboardService {

    private PatientRepository patientRepository;
    private DoctorRepository doctorRepository;
    private AppointmentRepository appointmentRepository;
    private MedicalRecordRepository medicalRecordRepository;
    private ModelMapper modelMapper;

    public DashboardServiceImpl(PatientRepository patientRepository,
            DoctorRepository doctorRepository,
            AppointmentRepository appointmentRepository,
            MedicalRecordRepository medicalRecordRepository,
            ModelMapper modelMapper) {
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
        this.appointmentRepository = appointmentRepository;
        this.medicalRecordRepository = medicalRecordRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Map<String, Long> getGlobalStats() {
        Map<String, Long> stats = new HashMap<>();
        stats.put("patients", patientRepository.count());
        stats.put("doctors", doctorRepository.count());
        stats.put("appointments", appointmentRepository.count());
        stats.put("medicalRecords", medicalRecordRepository.count());
        return stats;
    }

    @Override
    public List<AppointmentDto> getAppointmentsToday() {
        return appointmentRepository.findByAppointmentDate(LocalDate.now()).stream()
                .map(rdv -> modelMapper.map(rdv, AppointmentDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<AppointmentDto> getRecentActivity() {
        // Naive implementation: get last 10 RDVs. Ideally would have a real audit log.
        // JPA doesn't have "findTop10ByOrderByCreatedAtDesc" by default without adding
        // it to Repo.
        // I'll leave it as finding today's for now or just all (careful with size).
        // Let's rely on Repo having findAll.
        return appointmentRepository.findAll().stream()
                .sorted((a, b) -> b.getCreatedAt().compareTo(a.getCreatedAt()))
                .limit(5)
                .map(rdv -> modelMapper.map(rdv, AppointmentDto.class))
                .collect(Collectors.toList());
    }
}
