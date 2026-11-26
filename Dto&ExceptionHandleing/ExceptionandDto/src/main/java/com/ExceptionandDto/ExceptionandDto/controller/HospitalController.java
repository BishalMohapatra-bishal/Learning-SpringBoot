package com.ExceptionandDto.ExceptionandDto.controller;

import com.ExceptionandDto.ExceptionandDto.dto.HospitalRequestDto;
import com.ExceptionandDto.ExceptionandDto.dto.HospitalResponseDto;
import com.ExceptionandDto.ExceptionandDto.service.HospitalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/hospitals")
@RequiredArgsConstructor
public class HospitalController {

    private final HospitalService hospitalService;

    @PostMapping
    public ResponseEntity<HospitalResponseDto> createHospital(@Valid @RequestBody HospitalRequestDto requestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(hospitalService.createHospital(requestDto));
    }
    @GetMapping("/{id}")
    public ResponseEntity<HospitalResponseDto> getHospitalById(@PathVariable Long id) {
        return ResponseEntity.ok(hospitalService.getHospitalById(id));
    }

    @GetMapping
    public ResponseEntity<List<HospitalResponseDto>> getAllHospital() {
        return ResponseEntity.ok(hospitalService.getAllHospital());
    }
}