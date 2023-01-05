package com.example.appointment.controller;

import com.example.appointment.model.Appointment;
import com.example.appointment.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/v1/appointments")
public class AppointmentRestController {

    @Autowired
    private AppointmentService appointmentService;

    public AppointmentRestController() {
    }

    public AppointmentRestController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    /**
     * Admin can open home page which list all todays appointments
     **/
    @RequestMapping(path = "/", method = RequestMethod.GET)
    List<Appointment> findAll() {
        return appointmentService.findAll();
    }

    /**
     * Admin can add new appointment
     * POST request to add new appointments
     **/
    @RequestMapping(method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Appointment create(@RequestBody Appointment appointment) {
        return appointmentService.create(appointment);
    }


    /**
     * Admin can filter appointments by date
     * GET request to return all appointments based on a date range
     **/
    @RequestMapping(method = RequestMethod.GET)
    public List<Appointment> findByDateRange(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam("startDate") LocalDate startDate,
                                             @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam("endDate") LocalDate endDate) {
        return appointmentService.findByDateRange(startDate, endDate);
    }


    /**
     * Admin can filter appointments by patient name
     * GET request to return specific appointments
     **/
    @RequestMapping(path = "/{patientName}", method = RequestMethod.GET)
    public List<Appointment> findByPatientName(@PathVariable String patientname) {
        return appointmentService.findByPatientName(patientname);
    }


    /**
     * Admin can cancel and appointment and log the reason
     * PATCH request to update status of an appointment
     **/
    @RequestMapping(path = "/{appointmentId}", method = RequestMethod.PATCH, produces = "application/json", consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public Appointment updateStatus(@PathVariable Long appointmentId, @RequestBody Appointment appointment) {
        return appointmentService.updateStatus(appointmentId, appointment);
    }

    /**
     * DELETE request to delete specific appointments
     **/
    @RequestMapping(path = "/{appointmentId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    void deleteById(@PathVariable Long appointmentId) {
        appointmentService.deleteById(appointmentId);
    }
}
