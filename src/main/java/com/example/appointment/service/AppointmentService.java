package com.example.appointment.service;

import com.example.appointment.model.Appointment;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AppointmentService {

    List<Appointment> findByPatientName(String patientName);

    List<Appointment> findAll();

    List<Appointment> findByDateRange(LocalDate startDate, LocalDate endDate);

    Appointment create(Appointment appointment);

    Appointment update(Long appointmentId, Appointment appointment);

    Appointment updateStatus(Long appointmentId, Appointment appointment);

    void deleteById(Long appointmentId);

}
