package com.RelationShip.RelationShip.Controller;

import com.RelationShip.RelationShip.model.Student;
import com.RelationShip.RelationShip.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stud")
public class StudentController {
    private final StudentService studentService;

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        studentService.createStudent(student);
        return ResponseEntity.ok(student);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, Student student) {
        studentService.updateStudent(id, student);
        return ResponseEntity.ok(student);
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getStudentById(@PathVariable Long id) {
        studentService.getStudentById(id);
        return ResponseEntity.ok("OK");
    }

    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> returnList = studentService.getAllStudent();
        return ResponseEntity.ok(returnList);
    }

}
