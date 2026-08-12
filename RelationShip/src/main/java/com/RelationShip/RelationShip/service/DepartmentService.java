package com.RelationShip.RelationShip.service;

import com.RelationShip.RelationShip.model.Department;
import com.RelationShip.RelationShip.repository.DepartmentRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentRepository dRepo;

    public Department createStudent(Department department) {
        if (department == null) throw new RuntimeException("Missing student object!!");
        return dRepo.save(department);
    }

    public Department updateStudent(Long id, Department department) {
        Department existDepartment = dRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("No object found for the provided id!!"));
        existDepartment.setDept_name(department.getDept_name());

        return dRepo.save(existDepartment);
    }

    public Optional<Department> getDepartmentById(Long id) {
        return dRepo.findById(id);
    }

    public List<Department> getAllDepartment() {
        return dRepo.findAll();
    }

    public void deleteSDepartmentById(Long id) {
        dRepo.deleteById(id);
    }

    public void deleteAll() {
        dRepo.deleteAll();
    }
}
