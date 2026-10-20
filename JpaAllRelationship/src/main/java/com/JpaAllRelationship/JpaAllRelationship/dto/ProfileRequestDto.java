package com.JpaAllRelationship.JpaAllRelationship.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ProfileRequestDto(

        @NotBlank(message = "Bio must be filled")
        @Size(min = 5, max = 150, message = "Bio must be with in 25 to 150 characters")
        String bio
) {
}
