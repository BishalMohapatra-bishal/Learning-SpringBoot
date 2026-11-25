package com.ExceptionandDto.ExceptionandDto.service;

import com.ExceptionandDto.ExceptionandDto.dto.DoctorRequestDto;
import com.ExceptionandDto.ExceptionandDto.dto.DoctorResponseDto;
import com.ExceptionandDto.ExceptionandDto.entity.Doctor;
import com.ExceptionandDto.ExceptionandDto.entity.Hospital;
import com.ExceptionandDto.ExceptionandDto.globalExceptionHandler.ResourceNotFoundException;
import com.ExceptionandDto.ExceptionandDto.repository.DoctorRepository;
import com.ExceptionandDto.ExceptionandDto.repository.HospitalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final HospitalRepository hospitalRepository;

    @Transactional
    public DoctorResponseDto createDoctor(DoctorRequestDto doctorRequestDto) {
        Hospital hospital = hospitalRepository.findById(doctorRequestDto.hospitalId())
                .orElseThrow(() -> new ResourceNotFoundException("Hospital not found with ID: " + doctorRequestDto.hospitalId()));

        Doctor doctor = new Doctor();
        doctor.setName(doctorRequestDto.name());
        doctor.setSpecialized(doctorRequestDto.specialization());
        doctor.setPhoneNumber(doctorRequestDto.phoneNumber());

        hospital.addDoctor(doctor);
//        doctor.setHospital(hospital); // it only set the one site of entity relationship
//      if needed the doctor should be set
        Doctor saved = doctorRepository.save(doctor);

        return mapToDoctorResponseDto(saved);
    }

    @Transactional(readOnly = true)
    public DoctorResponseDto getDoctorById(Long id) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No doctor found with id" + id));
        return mapToDoctorResponseDto(doctor);
    }

    @Transactional(readOnly = true)
    public List<DoctorResponseDto> getAllDoctors() {
        return doctorRepository.findAll().stream()
                .map(this::mapToDoctorResponseDto)
                .toList();
    }


    private DoctorResponseDto mapToDoctorResponseDto(Doctor doctor) {
        return new DoctorResponseDto(
                doctor.getId(),
                doctor.getName(),
                doctor.getSpecialized(),
                doctor.getHospital().getId(),
                doctor.getHospital().getName()
        );
    }
}
