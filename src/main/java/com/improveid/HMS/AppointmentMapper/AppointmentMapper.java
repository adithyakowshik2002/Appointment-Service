package com.improveid.HMS.AppointmentMapper;

import com.improveid.HMS.Entity.Appointment;
import com.improveid.HMS.dto.request.AppointmentRequest;
import com.improveid.HMS.dto.response.AppointmentResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AppointmentMapper {

    @Autowired
    private ModelMapper modelMapper;

    public Appointment toEntity(AppointmentRequest  request){
        return modelMapper.map(request,Appointment.class);
    }

    public AppointmentResponse toResponse(Appointment entity){
        return modelMapper.map(entity,AppointmentResponse.class);
    }

}
