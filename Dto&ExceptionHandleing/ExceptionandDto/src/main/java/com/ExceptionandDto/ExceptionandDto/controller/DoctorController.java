package com.ExceptionandDto.ExceptionandDto.controller;

import com.ExceptionandDto.ExceptionandDto.dto.DoctorRequestDto;
import com.ExceptionandDto.ExceptionandDto.dto.DoctorResponseDto;
import com.ExceptionandDto.ExceptionandDto.service.DoctorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/doctors")
public class DoctorController {

    private final DoctorService doctorService;

    @PostMapping
    public ResponseEntity<DoctorResponseDto> createDoctor(@Valid @RequestBody DoctorRequestDto doctorRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(doctorService.createDoctor(doctorRequestDto));
    }

    @GetMapping("{id}")
    public ResponseEntity<DoctorResponseDto> getDoctorById(@PathVariable Long id) {
        return ResponseEntity.ok(doctorService.getDoctorById(id));
    }

    @GetMapping
    public ResponseEntity<List<DoctorResponseDto>> getAllDoctors() {
        return ResponseEntity.ok(doctorService.getAllDoctors());
    }
}
