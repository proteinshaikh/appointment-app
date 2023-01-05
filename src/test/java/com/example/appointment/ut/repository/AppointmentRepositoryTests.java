package com.example.appointment.ut.repository;

import com.example.appointment.model.Appointment;
import com.example.appointment.repository.AppointmentRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.sql.Time;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@RunWith(SpringRunner.class)
@DataJpaTest
public class AppointmentRepositoryTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AppointmentRepository appointmentRepository;


    @Test
    public void whenFindAllByAppointmentDateBetweenOrderByPriceAsc_thenReturnAppointmentName() {

        Appointment appointmenFirst = new Appointment("Test", LocalDate.of(2023, 2, 3), Time.valueOf("11:00:00"), Time.valueOf("12:00:00"), "Zeeshan", new BigDecimal(90));
        Appointment appointmentSecond = new Appointment("Test", LocalDate.of(2023, 2, 15), Time.valueOf("14:00:00"), Time.valueOf("15:00:00"), "Akram", new BigDecimal(60));
        Appointment appointmentThird = new Appointment("Test", LocalDate.of(2023, 1, 23), Time.valueOf("18:00:00"), Time.valueOf("19:00:00"), "Shaikh", new BigDecimal(10));

        entityManager.persist(appointmenFirst);
        entityManager.persist(appointmentSecond);
        entityManager.persist(appointmentThird);
        entityManager.flush();

        List<Appointment> appointments = appointmentRepository.findAllByAppointmentDateBetween(LocalDate.of(2023, 1, 20), LocalDate.of(2023, 2, 3));

        assertThat(appointments).extracting(Appointment::getNameOfDoctor).containsOnly(appointmenFirst.getNameOfDoctor(), appointmentThird.getNameOfDoctor());

    }

    @Test
    public void whenInvalidDate_thenThrowException() {

        Appointment appointmenFirst = new Appointment("Test", LocalDate.of(2023, 2, 3), Time.valueOf("11:00:00"), Time.valueOf("12:00:00"), "Zeeshan", new BigDecimal(90));
        Appointment appointmentSecond = new Appointment("Test", LocalDate.of(2023, 2, 15), Time.valueOf("14:00:00"), Time.valueOf("15:00:00"), "Shaikh", new BigDecimal(60));
        Appointment appointmentThird = new Appointment("Test", LocalDate.of(2023, 1, 23), Time.valueOf("18:00:00"), Time.valueOf("19:00:00"), "Test", new BigDecimal(10));

        entityManager.persist(appointmenFirst);
        entityManager.persist(appointmentSecond);
        entityManager.persist(appointmentThird);
        entityManager.flush();

        assertThrows(DateTimeException.class, () -> {
            appointmentRepository.findAllByAppointmentDateBetween(LocalDate.of(20, 1, 2023), LocalDate.of(2023, 22, 32));
        });
    }

}
