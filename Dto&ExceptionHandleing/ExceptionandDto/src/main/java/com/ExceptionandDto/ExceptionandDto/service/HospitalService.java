package com.ExceptionandDto.ExceptionandDto.service;

import com.ExceptionandDto.ExceptionandDto.dto.HospitalRequestDto;
import com.ExceptionandDto.ExceptionandDto.dto.HospitalResponseDto;
import com.ExceptionandDto.ExceptionandDto.entity.Hospital;
import com.ExceptionandDto.ExceptionandDto.globalExceptionHandler.ResourceNotFoundException;
import com.ExceptionandDto.ExceptionandDto.repository.HospitalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HospitalService {

    private final HospitalRepository hospitalRepository;

    @Transactional
    public HospitalResponseDto createHospital(HospitalRequestDto hospitalRequestDto) {
        Hospital hospital = new Hospital();
        hospital.setName(hospitalRequestDto.name());
        hospital.setAddress(hospitalRequestDto.address());
        hospital.setPhoneNumber(hospitalRequestDto.phoneNumber());

        Hospital savedHospital = hospitalRepository.save(hospital);
        return mapToHospitalResponseDto(savedHospital);
    }

    public HospitalResponseDto getHospitalById(Long id) {
        Hospital hospital = hospitalRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Hospital not found with ID: " + id));
        return mapToHospitalResponseDto(hospital);
    }

    public List<HospitalResponseDto> getAllHospital() {
        return hospitalRepository.findAll()
                .stream()
                .map(this::mapToHospitalResponseDto)
                .toList();
    }
    private HospitalResponseDto mapToHospitalResponseDto(Hospital hospital) {
        return new HospitalResponseDto(
                hospital.getId(),
                hospital.getName(),
                hospital.getAddress(),
                hospital.getPhoneNumber()
                );
    }
}
