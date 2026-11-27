package com.ExceptionandDto.ExceptionandDto.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.print.Doc;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Size(min = 0, max = 13, message = "phone number must be enter")
    private String phoneNumber;

    @Email
    private String email;

    private LocalDate dateOfBirth;

    private String address;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<Appointment> appointments = new ArrayList<>();

    public void addAppointment(Appointment appointment) {
        this.appointments.add(appointment);
        appointment.setPatient(this);
    }
    
    public void removeAppointment(Appointment appointment) {
        this.appointments.remove(appointment);
        appointment.setPatient(null);
    }
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "doc_id", nullable = false)
//    private Doctor doctor;
}
