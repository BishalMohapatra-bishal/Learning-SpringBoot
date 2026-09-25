package com.ExceptionandDto.ExceptionandDto.dto;

import com.ExceptionandDto.ExceptionandDto.Constants.AppointmentStatus;

import java.time.LocalDateTime;

public record AppointmentResponseDto(

        Long appointmentId,
        Long patientId,
        String patientName,
        Long doctorId,
        String doctorName,
        String hospitalName,
        LocalDateTime appointmentTime,
        AppointmentStatus status,
        String reason
) {}
