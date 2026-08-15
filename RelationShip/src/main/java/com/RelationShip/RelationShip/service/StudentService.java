package com.RelationShip.RelationShip.service;

import com.RelationShip.RelationShip.model.Student;
import com.RelationShip.RelationShip.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository sRepo;

    public Student createStudent(Student student) {
        if (student == null) throw new IllegalArgumentException("Missing student object!");
        return sRepo.save(student);
    }

    public Student updateStudent(Long id, Student studentDetails) {
        Student existStudent = sRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("No student found with id: " + id));

        existStudent.setStud_name(studentDetails.getStud_name());

        if (studentDetails.getDepartment() != null) {
            existStudent.setDepartment(studentDetails.getDepartment());
        }

        return sRepo.save(existStudent);
    }

    public Student getStudentById(Long id) {
        return sRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("No student found with id: " + id));
    }

    public List<Student> getAllStudents() {
        return sRepo.findAll();
    }

    public void deleteStudentById(Long id) {
        if (!sRepo.existsById(id)) {
            throw new RuntimeException("No student found with id: " + id);
        }
        sRepo.deleteById(id);
    }

    public void deleteAll() {
        sRepo.deleteAll();
    }
}
