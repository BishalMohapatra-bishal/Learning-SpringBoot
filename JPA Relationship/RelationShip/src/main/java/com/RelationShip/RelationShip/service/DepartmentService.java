package com.RelationShip.RelationShip.service;

import com.RelationShip.RelationShip.model.Department;
import com.RelationShip.RelationShip.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentRepository dRepo;

    public Department createDepartment(Department department) {
        if (department == null) throw new IllegalArgumentException("Missing department object!");

        // Link bidirectional mapping if department is saved with student list inside JSON
        if (department.getStudentsList() != null) {
            department.getStudentsList().forEach(student -> student.setDepartment(department));
        }

        return dRepo.save(department);
    }

    public Department updateDepartment(Long id, Department departmentDetails) {
        Department existDepartment = dRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("No department found with id: " + id));

        existDepartment.setDept_name(departmentDetails.getDept_name());

        return dRepo.save(existDepartment);
    }

    public Department getDepartmentById(Long id) {
        return dRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("No department found with id: " + id));
    }

    public List<Department> getAllDepartments() {
        return dRepo.findAll();
    }

    public void deleteDepartmentById(Long id) {
        if (!dRepo.existsById(id)) {
            throw new RuntimeException("No department found with id: " + id);
        }
        dRepo.deleteById(id);
    }

    public void deleteAll() {
        dRepo.deleteAll();
    }
}
