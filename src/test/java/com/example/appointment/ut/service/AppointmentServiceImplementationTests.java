package com.example.appointment.ut.service;

import com.example.appointment.model.Appointment;
import com.example.appointment.repository.AppointmentRepository;
import com.example.appointment.service.AppointmentService;
import com.example.appointment.service.AppointmentServiceImplementation;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.sql.Time;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class AppointmentServiceImplementationTests {

    @TestConfiguration
    static class EmployeeServiceImplTestContextConfiguration {
        @Bean
        public AppointmentService appointmentService() {
            return new AppointmentServiceImplementation();
        }
    }

    @Autowired
    private AppointmentService appointmentService;

    @MockBean
    private AppointmentRepository appointmentRepository;

    @Before
    public void setUp() {

        Appointment appointmenFirst = new Appointment("Test1",LocalDate.of(2023, 2, 3), Time.valueOf("11:00:00"), Time.valueOf("12:00:00"), "Test", new BigDecimal(90));
        Appointment appointmentSecond = new Appointment("Test2",LocalDate.of(2023, 2, 15), Time.valueOf("14:00:00"), Time.valueOf("15:00:00"), "Test1", new BigDecimal(60));
        Appointment appointmentThird = new Appointment("Test3",LocalDate.of(2023, 1, 23), Time.valueOf("18:00:00"), Time.valueOf("19:00:00"), "Test2", new BigDecimal(10));

        List<Appointment> allAppointments = Arrays.asList(appointmenFirst, appointmentSecond, appointmentThird);

        Mockito.when(appointmentRepository.findById(appointmenFirst.getId())).thenReturn(Optional.of(appointmenFirst));
        Mockito.when(appointmentRepository.findAll()).thenReturn(allAppointments);
        Mockito.when(appointmentRepository.findById(-99L)).thenReturn(Optional.empty());
    }



    @Test
    public void whenFindAll_thenReturnAllRecords() {
        Appointment appointmenFirst = new Appointment("Test1",LocalDate.of(2023, 2, 3), Time.valueOf("11:00:00"), Time.valueOf("12:00:00"), "Test", new BigDecimal(90));
        Appointment appointmentSecond = new Appointment("Test2",LocalDate.of(2023, 2, 15), Time.valueOf("14:00:00"), Time.valueOf("15:00:00"), "Test1", new BigDecimal(60));
        Appointment appointmentThird = new Appointment("Test3",LocalDate.of(2023, 1, 23), Time.valueOf("18:00:00"), Time.valueOf("19:00:00"), "Test2", new BigDecimal(10));

        List<Appointment> allAppointments = appointmentService.findAll();
        verifyFindAllEmployeesIsCalledOnce();
        assertThat(allAppointments).hasSize(3).extracting(Appointment::getNameOfDoctor).contains(appointmenFirst.getNameOfDoctor(), appointmentSecond.getNameOfDoctor(), appointmentThird.getNameOfDoctor());
    }


    private void verifyFindByIdIsCalledOnce() {
        Mockito.verify(appointmentRepository, VerificationModeFactory.times(1)).findById(Mockito.anyLong());
        Mockito.reset(appointmentRepository);
    }

    private void verifyFindAllEmployeesIsCalledOnce() {
        Mockito.verify(appointmentRepository, VerificationModeFactory.times(1)).findAll();
        Mockito.reset(appointmentRepository);
    }

}
