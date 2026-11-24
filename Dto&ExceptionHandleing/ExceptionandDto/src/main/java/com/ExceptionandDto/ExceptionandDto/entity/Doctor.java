package com.ExceptionandDto.ExceptionandDto.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String specialized;

    @Size(min = 0, max = 13, message = "Phone number needed")
    private String phoneNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hospital_id", nullable = false)
    private Hospital hospital;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
    private List<Appointment> appointments = new ArrayList<>();

    public void addAppointment(Appointment appointment) {
        this.appointments.add(appointment);
        appointment.setDoctor(this);
    }

    public void removeAppointment(Appointment appointment) {
        this.appointments.remove(appointment);
        appointment.setDoctor(null);
    }
//    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
//    private List<Patient> patient = new ArrayList<>();
}
