package com.JpaAllRelationship.JpaAllRelationship.repository;

import com.JpaAllRelationship.JpaAllRelationship.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
}
