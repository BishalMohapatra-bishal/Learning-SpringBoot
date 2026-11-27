package com.ExceptionandDto.ExceptionandDto.service;

import com.ExceptionandDto.ExceptionandDto.dto.PatientRequestDto;
import com.ExceptionandDto.ExceptionandDto.dto.PatientResponseDto;
import com.ExceptionandDto.ExceptionandDto.entity.Patient;
import com.ExceptionandDto.ExceptionandDto.globalExceptionHandler.DuplicateResourceException;
import com.ExceptionandDto.ExceptionandDto.globalExceptionHandler.ResourceNotFoundException;
import com.ExceptionandDto.ExceptionandDto.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
//@Transactional(readOnly = true)
public class PatientService {

    private final PatientRepository patientRepository;

    @Transactional
    public PatientResponseDto registerPatient(PatientRequestDto patientRequestDto) {
        if (patientRepository.existsByEmail(patientRequestDto.email())) {
            throw new DuplicateResourceException("Patient with email " + patientRequestDto.email() + " already exists");
        }

        Patient patient = new Patient();
        patient.setName(patientRequestDto.name());
        patient.setEmail(patientRequestDto.email());
        patient.setPhoneNumber(patientRequestDto.phoneNumber());
        patient.setAddress(patientRequestDto.address());

        Patient saved = patientRepository.save(patient);
//        return new PatientResponseDto(saved.getId(), saved.getName(), saved.getEmail(), saved.getPhoneNumber(), saved.getDateOfBirth());
          return mapToResponseDto(saved);
    }

    @Transactional(readOnly = true)
    public PatientResponseDto getPatientById(Long id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found withId: " + id));

        return mapToResponseDto(patient);
    }

    @Transactional(readOnly = true)
    public List<PatientResponseDto> getAllPatients() {
        return patientRepository.findAll().stream()
                                          .map(this::mapToResponseDto)
                                          .toList();
    }

    private PatientResponseDto mapToResponseDto(Patient patient) {
        return new PatientResponseDto(
                patient.getId(),
                patient.getName(),
                patient.getEmail(),
                patient.getPhoneNumber(),
                patient.getDateOfBirth()
        );
    }
}
