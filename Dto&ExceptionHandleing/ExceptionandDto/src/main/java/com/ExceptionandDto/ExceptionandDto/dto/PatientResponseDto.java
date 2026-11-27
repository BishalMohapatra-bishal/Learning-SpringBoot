package com.ExceptionandDto.ExceptionandDto.dto;

import java.time.LocalDate;

public record PatientResponseDto(
        Long id,
        String name,
        String email,
        String phoneNumber,
        LocalDate dateOfBirth
) {}

