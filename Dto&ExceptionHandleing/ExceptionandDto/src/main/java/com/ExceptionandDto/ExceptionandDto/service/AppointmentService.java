package com.ExceptionandDto.ExceptionandDto.service;

import com.ExceptionandDto.ExceptionandDto.Constants.AppointmentStatus;
import com.ExceptionandDto.ExceptionandDto.dto.AppointmentRequestDto;
import com.ExceptionandDto.ExceptionandDto.dto.AppointmentResponseDto;
import com.ExceptionandDto.ExceptionandDto.entity.Appointment;
import com.ExceptionandDto.ExceptionandDto.entity.Doctor;
import com.ExceptionandDto.ExceptionandDto.entity.Patient;
import com.ExceptionandDto.ExceptionandDto.globalExceptionHandler.AppointmentConflictException;
import com.ExceptionandDto.ExceptionandDto.globalExceptionHandler.ResourceNotFoundException;
import com.ExceptionandDto.ExceptionandDto.repository.AppointmentRepository;
import com.ExceptionandDto.ExceptionandDto.repository.DoctorRepository;
import com.ExceptionandDto.ExceptionandDto.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    @Transactional
    public AppointmentResponseDto bookAppointment(AppointmentRequestDto appointmentRequestDto) {
        Patient patient = patientRepository.findById(appointmentRequestDto.patientId())
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found with ID" + appointmentRequestDto.patientId()));

        Doctor doctor = doctorRepository.findById(appointmentRequestDto.doctorId())
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found with ID" + appointmentRequestDto.doctorId()));

        boolean isSlotTaken = appointmentRepository.existsByDoctorIdAndAppointmentTimeAndStatus(
                doctor.getId(), appointmentRequestDto.appointmentTime(), AppointmentStatus.CONFIRMED
        );

        if (isSlotTaken) {
            throw new AppointmentConflictException("Doctor is already booked at: " + appointmentRequestDto.appointmentTime());
        }

        Appointment appointment = new Appointment();
//        appointment.setPatient(patient);
//        appointment.setDoctor(doctor);

        doctor.addAppointment(appointment);
        patient.addAppointment(appointment);
        appointment.setAppointment_time(appointmentRequestDto.appointmentTime());
        appointment.setStatus(AppointmentStatus.CONFIRMED);
        appointment.setReason(appointmentRequestDto.reason());

        Appointment saved = appointmentRepository.save(appointment);

        return mapToAppointmentResponseDto(saved);
    }

    public AppointmentResponseDto getAppointmentById(Long id) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment not found with id" + id));
        return mapToAppointmentResponseDto(appointment);
    }

    public List<AppointmentResponseDto> getAllAppointments() {
        return appointmentRepository.findAll()
                .stream()
                .map(this::mapToAppointmentResponseDto)
                .toList();
    }

    private AppointmentResponseDto mapToAppointmentResponseDto(Appointment appointment) {
        return new AppointmentResponseDto(
                appointment.getId(),
                appointment.getPatient().getId(),
                appointment.getPatient().getName(),
                appointment.getDoctor().getId(),
                appointment.getDoctor().getName(),
                appointment.getDoctor().getHospital().getName(),
                appointment.getAppointment_time(),
                appointment.getStatus(),
                appointment.getReason()
        );
    }
}
