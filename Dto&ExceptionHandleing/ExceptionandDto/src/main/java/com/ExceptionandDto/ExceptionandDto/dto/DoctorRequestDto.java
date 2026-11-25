package com.ExceptionandDto.ExceptionandDto.dto;

import jakarta.validation.constraints.*;

public record DoctorRequestDto(

        @NotBlank(message = "Doctor name is required")
        @Size(min = 2, max = 100, message = "Name must be with in 2 to 100 characters")
        String name,

        @NotBlank(message = "Specialization is required")
        String specialization,

        @NotBlank(message = "Phone number is required")
        @Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "Phone number must be valid and between 10 to 15 digits")
        String phoneNumber,

        @NotNull(message = "Hospital id is required")
        @Positive(message = "Hospital id must be a positive number")
        Long hospitalId
) {}
