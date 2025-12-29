package com.hospital.service;

import com.hospital.dto.AppointmentDto;
import java.util.List;
import java.util.Map;

public interface DashboardService {
    Map<String, Long> getGlobalStats();

    List<AppointmentDto> getAppointmentsToday();

    // Assuming activity is just recent RDVs for now
    List<AppointmentDto> getRecentActivity();
}
