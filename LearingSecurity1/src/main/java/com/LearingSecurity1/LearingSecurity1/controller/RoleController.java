package com.LearingSecurity1.LearingSecurity1.controller;

import com.LearingSecurity1.LearingSecurity1.entity.Role;
import com.LearingSecurity1.LearingSecurity1.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/roles")
public class RoleController {

    private final RoleService roleService;

    @PostMapping
    public ResponseEntity<String> addRole(@RequestBody Role role) {
        roleService.addRole(role);
        return ResponseEntity.ok("Done");
    }
}
