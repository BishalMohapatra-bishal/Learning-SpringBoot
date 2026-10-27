package com.JpaAllRelationship.JpaAllRelationship.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserRequestDto(

        @NotBlank(message = "User name is required")
        @Size(min = 5, max = 30, message = "Name must be with in the 5 and 30 characters")
        String name,

        @NotNull(message = "Profile Id is required")
        Long profileId
) {
}
