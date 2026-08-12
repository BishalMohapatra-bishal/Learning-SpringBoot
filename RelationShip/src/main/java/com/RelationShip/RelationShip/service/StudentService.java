package com.RelationShip.RelationShip.service;

import com.RelationShip.RelationShip.model.Student;
import com.RelationShip.RelationShip.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository sRepo;

    public Student createStudent(Student student) {
        if (student == null) throw new RuntimeException("Missing student object!!");
        return sRepo.save(student);
    }

    public Student updateStudent(Long id, Student student) {
//        Optional<Student> existStudent = sRepo.findById(id);
//
//        if (existStudent.isPresent()) {
//            Student dbStudent = existStudent.get();
//
//            dbStudent.setStud_name(student.getStud_name());
//    }
//    Recommended way
            Student existStudent = sRepo.findById(id)
                    .orElseThrow(() -> new RuntimeException("No object found for the provided id!!"));
            existStudent.setStud_name(student.getStud_name());

            return sRepo.save(existStudent);
        }

        public Optional<Student> getStudentById(Long id) {
            return sRepo.findById(id);
        }

        public List<Student> getAllStudent() {
            return sRepo.findAll();
        }

        public void deleteStudentById(Long id) {
            sRepo.deleteById(id);
        }

        public void deleteAll() {
            sRepo.deleteAll();
        }
    }

