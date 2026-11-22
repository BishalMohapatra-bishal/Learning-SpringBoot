package com.ExceptionandDto.ExceptionandDto.controller;

import com.ExceptionandDto.ExceptionandDto.dto.AppointmentRequestDto;
import com.ExceptionandDto.ExceptionandDto.dto.AppointmentResponseDto;
import com.ExceptionandDto.ExceptionandDto.service.AppointmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/appointment")
public class AppointmentController {

    private final AppointmentService appointmentService;

    @PostMapping("/create")
    public ResponseEntity<AppointmentResponseDto> registerAppointment(
            @Valid @RequestBody AppointmentRequestDto appointmentRequestDto) {
        AppointmentResponseDto appointmentResponseDto =
                appointmentService.bookAppointment(appointmentRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(appointmentResponseDto);
    }

    @GetMapping("/byId/{id}")
    public ResponseEntity<AppointmentResponseDto> getAppointmentById(@PathVariable Long id) {
        AppointmentResponseDto appointmentResponseDto =
                appointmentService.getAppointmentById(id);
        return ResponseEntity.ok(appointmentResponseDto);
    }

    @GetMapping("getAll")
    public ResponseEntity<List<AppointmentResponseDto>> getAllAppointment() {
        List<AppointmentResponseDto> appointmentResponseDtos = appointmentService.getAllAppointments();
        return ResponseEntity.ok(appointmentResponseDtos);
    }

}
