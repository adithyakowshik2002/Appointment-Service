package com.improveid.HMS.Entity;

import com.improveid.HMS.Enum.AppointmentStatus;
import com.improveid.HMS.Enum.AppointmentType;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

@Entity
@Table(name="appointments")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Appointment {

    @Id
    @Column(name = "appointment_id", updatable = false, nullable = false)
    private Long appointmentId;

    @Column(name = "doctor_id", nullable = false)
    private Long doctorId;

    @Column(name = "patient_id", nullable = false)
    private Long patientId;

    @Column(name = "appointment_date", nullable = false)
    private LocalDate appointmentDate;

    @Column(name = "time_slot",nullable = false)
    private LocalTime timeslot;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AppointmentType appointmentType =AppointmentType.OP;//Default to OP


    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AppointmentStatus status =AppointmentStatus.BOOKED;




}
