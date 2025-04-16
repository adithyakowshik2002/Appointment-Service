package com.improveid.HMS.repository;

import com.improveid.HMS.Entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    // To check if a slot is already booked for a doctor on a specific date
    boolean findByDoctorIdAndAppointmentDateAndTimeslot(Long doctorId, LocalDate appointmentDate, LocalTime timeSlot);

    // To fetch all appointments for a specific doctor on a specific date
    List<Appointment> findByDoctorIdAndAppointmentDate(Long doctorId, LocalDate appointmentDate);

    // To fetch all appointments for a patient
    Appointment findByPatientId(Long patientId);

}
