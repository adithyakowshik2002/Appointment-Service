package com.improveid.HMS.Controller;

import com.improveid.HMS.Entity.Appointment;
import com.improveid.HMS.dto.request.AppointmentRequest;
import com.improveid.HMS.dto.response.AppointmentResponse;
import com.improveid.HMS.service.AppointmentService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/appointments")
@Slf4j
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;



    @PostMapping("/book")
    public ResponseEntity<AppointmentResponse> bookOp(@Valid @RequestBody AppointmentRequest request) {
        log.info("Booking appointment for patientId={}, doctorId={}", request.getPatientId(), request.getDoctorId());
        AppointmentResponse appointment = appointmentService.bookAppointment(request);
        return new ResponseEntity<>(appointment, HttpStatus.CREATED);
    }

    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<List<AppointmentResponse>> getAppointmentsForDoctorOnDate(
            @PathVariable Long doctorId,
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        log.info("Fetching appointments for doctor {} on {}", doctorId, date);
        List<AppointmentResponse> appointments = appointmentService.getAppointmentsFromDoctorOnDate(doctorId, date);
        return new ResponseEntity<>(appointments, HttpStatus.OK);
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<AppointmentResponse> getAppointmentsForPatient(@PathVariable Long patientId) {
        log.info("Fetching appointments for patient {}", patientId);
        AppointmentResponse appointments = appointmentService.getAppointmentsForPatient(patientId);
        return new ResponseEntity<>(appointments, HttpStatus.OK);
    }

    /*
    @PostMapping("/convert-to-ip")
    public ResponseEntity<Appointment> convertToIp(@Valid @RequestBody ConvertToIpRequest request) {
        Appointment appointment = appointmentService.convertToIp(request);
        return ResponseEntity.ok(appointment);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Appointment> getAppointment(@PathVariable UUID id) {
        return ResponseEntity.ok(appointmentService.getAppointmentById(id));
    }*/

}
