package com.JpaAllRelationship.JpaAllRelationship.dto;

public record UserResponseDto(

        Long id,
        String name,
        Long profileId,
        String bio
) {
}
