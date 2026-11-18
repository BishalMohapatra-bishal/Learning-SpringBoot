package com.LearingSecurity1.LearingSecurity1.service;

import com.LearingSecurity1.LearingSecurity1.entity.Role;
import com.LearingSecurity1.LearingSecurity1.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    public void addRole(Role role) {
        roleRepository.save(role);
    }
}
