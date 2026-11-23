package com.ExceptionandDto.ExceptionandDto.repository;

import com.ExceptionandDto.ExceptionandDto.entity.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HospitalRepository extends JpaRepository<Hospital, Long> {
}
