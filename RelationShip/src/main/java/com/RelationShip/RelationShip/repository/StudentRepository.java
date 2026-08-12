package com.RelationShip.RelationShip.repository;

import com.RelationShip.RelationShip.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
