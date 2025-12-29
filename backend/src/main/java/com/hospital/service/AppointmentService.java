package com.hospital.service;

import com.hospital.dto.AppointmentDto;
import com.hospital.entity.AppointmentStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface AppointmentService {
    AppointmentDto createAppointment(AppointmentDto appointmentDto);

    AppointmentDto getAppointmentById(Long id);

    Page<AppointmentDto> getAllAppointments(Pageable pageable);

    AppointmentDto updateAppointment(Long id, AppointmentDto appointmentDto);

    AppointmentDto updateStatus(Long id, AppointmentStatus status);

    void deleteAppointment(Long id);

    List<AppointmentDto> getAppointmentsByPatientId(Long patientId);

    List<AppointmentDto> getAppointmentsByDoctorId(Long doctorId);

    List<AppointmentDto> getAppointmentsByDate(LocalDate date);
}
