package com.ExceptionandDto.ExceptionandDto.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record HospitalRequestDto(

        @NotBlank(message = "Hospital name is required")
        @Size(min = 2, max = 150, message = "Hospital name must be between 2 and 150 characters")
        String name,

        @NotBlank(message = "Hospital address is required")
        @Size(max = 225, message = "Address can not exceed 225 characters")
        String address,

        @NotBlank(message = "Phone number is required")
        @Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "Phone number must be valid and between 10 to 15 digits")
        String phoneNumber
) {}
