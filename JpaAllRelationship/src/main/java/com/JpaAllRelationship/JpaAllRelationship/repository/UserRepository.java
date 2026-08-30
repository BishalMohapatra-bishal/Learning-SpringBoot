package com.JpaAllRelationship.JpaAllRelationship.repository;

import com.JpaAllRelationship.JpaAllRelationship.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
