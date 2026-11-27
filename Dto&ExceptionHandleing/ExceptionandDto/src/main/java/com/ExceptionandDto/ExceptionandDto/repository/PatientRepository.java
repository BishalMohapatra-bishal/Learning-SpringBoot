package com.ExceptionandDto.ExceptionandDto.repository;

import com.ExceptionandDto.ExceptionandDto.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    boolean existsByEmail(String email);
}
