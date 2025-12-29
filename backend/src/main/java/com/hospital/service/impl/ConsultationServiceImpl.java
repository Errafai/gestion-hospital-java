package com.hospital.service.impl;

import com.hospital.dto.ConsultationDto;
import com.hospital.entity.Consultation;
import com.hospital.entity.MedicalRecord;
import com.hospital.entity.Doctor;
import com.hospital.entity.Appointment;
import com.hospital.entity.Patient;
import com.hospital.exception.ResourceNotFoundException;
import com.hospital.repository.ConsultationRepository;
import com.hospital.repository.MedicalRecordRepository;
import com.hospital.repository.DoctorRepository;
import com.hospital.repository.AppointmentRepository;
import com.hospital.service.ConsultationService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConsultationServiceImpl implements ConsultationService {

    private ConsultationRepository consultationRepository;
    private MedicalRecordRepository medicalRecordRepository;
    private DoctorRepository doctorRepository;
    private AppointmentRepository appointmentRepository;
    private ModelMapper modelMapper;

    public ConsultationServiceImpl(ConsultationRepository consultationRepository,
            MedicalRecordRepository medicalRecordRepository,
            DoctorRepository doctorRepository,
            AppointmentRepository appointmentRepository,
            ModelMapper modelMapper) {
        this.consultationRepository = consultationRepository;
        this.medicalRecordRepository = medicalRecordRepository;
        this.doctorRepository = doctorRepository;
        this.appointmentRepository = appointmentRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ConsultationDto createConsultation(ConsultationDto consultationDto) {
        MedicalRecord medicalRecord = medicalRecordRepository.findById(consultationDto.getMedicalRecordId())
                .orElseThrow(() -> new ResourceNotFoundException("MedicalRecord", "id",
                        consultationDto.getMedicalRecordId()));

        Doctor doctor = doctorRepository.findById(consultationDto.getDoctorId())
                .orElseThrow(() -> new ResourceNotFoundException("Doctor", "id", consultationDto.getDoctorId()));

        Consultation consultation = modelMapper.map(consultationDto, Consultation.class);
        consultation.setMedicalRecord(medicalRecord);
        consultation.setDoctor(doctor);

        if (consultationDto.getAppointmentId() != null) {
            Appointment appointment = appointmentRepository.findById(consultationDto.getAppointmentId())
                    .orElseThrow(
                            () -> new ResourceNotFoundException("Appointment", "id",
                                    consultationDto.getAppointmentId()));
            consultation.setAppointment(appointment);
        }

        Consultation saved = consultationRepository.save(consultation);
        return modelMapper.map(saved, ConsultationDto.class);
    }

    @Override
    public List<ConsultationDto> getAllConsultations() {
        return consultationRepository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    private ConsultationDto mapToDto(Consultation consultation) {
        ConsultationDto dto = modelMapper.map(consultation, ConsultationDto.class);

        // Map doctor name
        if (consultation.getDoctor() != null && consultation.getDoctor().getUser() != null) {
            dto.setDoctorName("Dr. " + consultation.getDoctor().getUser().getFirstName() + " "
                    + consultation.getDoctor().getUser().getLastName());
        }

        // Map patient info via medical record
        if (consultation.getMedicalRecord() != null && consultation.getMedicalRecord().getPatient() != null) {
            Patient patient = consultation.getMedicalRecord().getPatient();
            dto.setPatientId(patient.getId());
            dto.setPatientName(patient.getFirstName() + " " + patient.getLastName());
        }

        return dto;
    }

    @Override
    public ConsultationDto getConsultationById(Long id) {
        Consultation consultation = consultationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Consultation", "id", id));
        return modelMapper.map(consultation, ConsultationDto.class);
    }

    @Override
    public List<ConsultationDto> getConsultationsByMedicalRecordId(Long medicalRecordId) {
        return consultationRepository.findByMedicalRecordId(medicalRecordId).stream()
                .map(c -> modelMapper.map(c, ConsultationDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public ConsultationDto updateConsultation(Long id, ConsultationDto consultationDto) {
        Consultation consultation = consultationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Consultation", "id", id));

        consultation.setSymptoms(consultationDto.getSymptoms());
        consultation.setDiagnosis(consultationDto.getDiagnosis());
        consultation.setTreatment(consultationDto.getTreatment());
        consultation.setObservations(consultationDto.getObservations());
        consultation.setConsultationDate(consultationDto.getConsultationDate());

        Consultation updated = consultationRepository.save(consultation);
        return modelMapper.map(updated, ConsultationDto.class);
    }

    @Override
    public void deleteConsultation(Long id) {
        Consultation consultation = consultationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Consultation", "id", id));
        consultationRepository.delete(consultation);
    }
}
