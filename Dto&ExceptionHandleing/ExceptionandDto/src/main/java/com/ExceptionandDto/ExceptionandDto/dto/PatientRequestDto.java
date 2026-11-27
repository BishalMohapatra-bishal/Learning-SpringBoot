package com.ExceptionandDto.ExceptionandDto.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record PatientRequestDto (

   @NotBlank(message = "Patient name is required")
   @Size(min = 2, max = 100, message = "name must be between 2 and 100 characters")
   String name,

   @NotBlank(message = "Phone number is required")
   @Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "Phone number must be valid and between 10 to 15 digits")
   String phoneNumber,

   @NotBlank(message = "Email is required")
   @Email(message = "Please provide a valid email address")
   String email,

   @NotNull(message = "Date of birth is required")
   @Past(message = "Date of birth must be a date in the past")
   LocalDate dateOfBirth,

   String address
   ) {}
