package com.JpaAllRelationship.JpaAllRelationship.controller;

import com.JpaAllRelationship.JpaAllRelationship.dto.ProfileRequestDto;
import com.JpaAllRelationship.JpaAllRelationship.dto.ProfileResponseDto;
import com.JpaAllRelationship.JpaAllRelationship.service.ProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/profile")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

    @PostMapping("/create")
    public ResponseEntity<ProfileResponseDto> createProfile(@Valid @RequestBody ProfileRequestDto profileRequestDto) {
        ProfileResponseDto responseDto = profileService.createProfile(profileRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }
}
