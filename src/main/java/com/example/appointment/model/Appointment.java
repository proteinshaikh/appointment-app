package com.example.appointment.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;

@Data
@Entity
public class Appointment {

    private @Id
    @GeneratedValue Long id;


    private String patientName;
    private Timestamp createdAt = new Timestamp(System.currentTimeMillis());
    private LocalDate appointmentDate;
    private Time appointmentStartTime;
    private Time appointmentEndTime;
    private String nameOfDoctor;
    private AppointmentStatus status;
    private BigDecimal price;

    public Appointment() {

    }

    public Appointment(String patientName, Timestamp createdAt, LocalDate appointmentDate, Time appointmentStartTime, Time appointmentEndTime, String nameOfDoctor, AppointmentStatus status, BigDecimal price) {
        this.patientName = patientName;
        this.createdAt = createdAt;
        this.appointmentDate = appointmentDate;
        this.appointmentStartTime = appointmentStartTime;
        this.appointmentEndTime = appointmentEndTime;
        this.nameOfDoctor = nameOfDoctor;
        this.status = status;
        this.price = price;
    }

    public Appointment(Timestamp createdAt, LocalDate appointmentDate, Time appointmentStartTime, Time appointmentEndTime, String nameOfDoctor, AppointmentStatus status, BigDecimal price) {
        this.createdAt = createdAt;
        this.appointmentDate = appointmentDate;
        this.appointmentStartTime = appointmentStartTime;
        this.appointmentEndTime = appointmentEndTime;
        this.nameOfDoctor = nameOfDoctor;
        this.status = status;
        this.price = price;
    }

    public Appointment(String patientName, LocalDate appointmentDate, Time appointmentStartTime, Time appointmentEndTime, String nameOfDoctor, BigDecimal price) {
        this.patientName = patientName;
        this.appointmentDate = appointmentDate;
        this.appointmentStartTime = appointmentStartTime;
        this.appointmentEndTime = appointmentEndTime;
        this.nameOfDoctor = nameOfDoctor;
        this.price = price;
    }

    public Appointment(String patientName,LocalDate appointmentDate, String nameOfDoctor, BigDecimal price) {
        this.patientName = patientName;
        this.appointmentDate = appointmentDate;
        this.nameOfDoctor = nameOfDoctor;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(LocalDate appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public Time getAppointmentStartTime() {
        return appointmentStartTime;
    }

    public void setAppointmentStartTime(Time appointmentStartTime) {
        this.appointmentStartTime = appointmentStartTime;
    }

    public Time getAppointmentEndTime() {
        return appointmentEndTime;
    }

    public void setAppointmentEndTime(Time appointmentEndTime) {
        this.appointmentEndTime = appointmentEndTime;
    }

    public String getNameOfDoctor() {
        return nameOfDoctor;
    }

    public void setNameOfDoctor(String nameOfDoctor) {
        this.nameOfDoctor = nameOfDoctor;
    }

    public AppointmentStatus getStatus() {
        return status;
    }

    public void setStatus(AppointmentStatus status) {
        this.status = status;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
