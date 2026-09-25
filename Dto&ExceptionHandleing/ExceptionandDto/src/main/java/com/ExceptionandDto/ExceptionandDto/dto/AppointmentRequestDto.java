package com.ExceptionandDto.ExceptionandDto.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

public record AppointmentRequestDto(

        @NotNull(message = "Patient id is required")
        @Positive(message = "Patient id must be valid")
        Long patientId,

        @NotNull(message = "Doctor id is required")
        @Positive(message = "Doctor id must be valid")
        Long doctorId,

        @NotNull(message = "Appointment date and time is required")
        @Future(message = "Appointment date and time must be scheduled for future")
        LocalDateTime appointmentTime,


        @NotBlank(message = "Reason for appointment is required")
        @Size(max = 225, message = "Reason cannot exceed 225 characters")
        String reason
) {}
