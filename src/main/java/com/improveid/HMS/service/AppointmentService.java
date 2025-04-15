package com.improveid.HMS.service;
import com.improveid.HMS.AppointmentMapper.AppointmentMapper;
import com.improveid.HMS.Entity.Appointment;
import com.improveid.HMS.Enum.AppointmentStatus;
import com.improveid.HMS.dto.request.AppointmentRequest;
import com.improveid.HMS.dto.response.AppointmentResponse;
import com.improveid.HMS.repository.AppointmentRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;

import java.util.stream.Collectors;

@Log4j2
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private AppointmentMapper appointmentMapper;

    public AppointmentResponse bookAppointment(AppointmentRequest request){
        log.info("Booking appointment for doctorId: {}, patientId: {}, date: {}, time: {}",
                request.getDoctorId(), request.getPatientId(),
                request.getAppointmentDate(), request.getTimeslot());

        boolean isSlotBooked = appointmentRepository.findByDoctorIdAndAppointmentDateAndTimeslot(request.getDoctorId(),request.getAppointmentDate(),request.getTimeslot());

        if(isSlotBooked){
        log.info("Doctor is already booked at the requested slot.");
        throw new RuntimeException("This time slot is already booked for the selected doctor");
        }
        Appointment appointment = appointmentMapper.toEntity(request);
        log.info("Appointment booked with ID: {}", appointment.getAppointmentId());
        if(appointment.getStatus()==null)
       {
           appointment.setStatus(AppointmentStatus.BOOKED);
       }

        return appointmentMapper.toResponse(appointment);
    }

    /**
     * Get all appointments for a specific doctor on a specific date
     */
    public List<AppointmentResponse> getAppointmentsFromDoctorOnDate(Long doctorId, LocalDate date){

        log.info("Fetching appointments for doctorId: {} on {}", doctorId, date);
        List<Appointment> appointments = appointmentRepository.findByDoctorIdAndAppointmentDate(doctorId,date);

        return appointments.stream().map(appointmentMapper::toResponse).collect(Collectors.toList());
    }

    public AppointmentResponse getAppointmentsForPatient(Long patientId) {
        log.info("Fetching appointments for patientId: {}", patientId);
        Appointment appointments = appointmentRepository.findByPatientId(patientId);
        return appointmentMapper.toResponse(appointments);
    }
}
