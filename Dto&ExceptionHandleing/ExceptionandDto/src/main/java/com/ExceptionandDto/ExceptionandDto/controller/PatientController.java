package com.ExceptionandDto.ExceptionandDto.controller;

import com.ExceptionandDto.ExceptionandDto.dto.PatientRequestDto;
import com.ExceptionandDto.ExceptionandDto.dto.PatientResponseDto;
import com.ExceptionandDto.ExceptionandDto.service.PatientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/patients")
public class PatientController {

    private final PatientService patientService;

    @PostMapping
    public ResponseEntity<PatientResponseDto> createPatient(@Valid @RequestBody PatientRequestDto requestDto) {
//        return new ResponseEntity<>(patientService.registerPatient(requestDto), HttpStatus.CREATED);
        return ResponseEntity.status(HttpStatus.CREATED).body(patientService.registerPatient(requestDto));
    }

    @GetMapping("{id}")
    public ResponseEntity<PatientResponseDto> getPatientById(@PathVariable Long id) {
        return ResponseEntity.ok(patientService.getPatientById(id));
    }

    public ResponseEntity<List<PatientResponseDto>> getAllPatient() {
        return ResponseEntity.ok(patientService.getAllPatients());
    }
}
