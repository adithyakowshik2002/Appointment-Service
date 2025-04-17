package com.improveid.HMS.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalTime;

@FeignClient(name = "DOCTOR-SERVICE")
public interface DoctorClient {

}
