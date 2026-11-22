package com.ExceptionandDto.ExceptionandDto.repository;

import com.ExceptionandDto.ExceptionandDto.Constants.AppointmentStatus;
import com.ExceptionandDto.ExceptionandDto.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    boolean existsByDoctorIdAndAppointmentTimeAndStatus(
      Long doctorId,
      LocalDateTime appointmentTime,
      AppointmentStatus status
    );
}
