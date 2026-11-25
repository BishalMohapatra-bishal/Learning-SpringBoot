package com.ExceptionandDto.ExceptionandDto.dto;

public record DoctorResponseDto(

        Long id,
        String name,
        String specialization,
        Long hospitalId,
        String hospitalName
) {}
