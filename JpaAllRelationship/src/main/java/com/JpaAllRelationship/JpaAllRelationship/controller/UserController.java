package com.JpaAllRelationship.JpaAllRelationship.controller;

import com.JpaAllRelationship.JpaAllRelationship.dto.UserRequestDto;
import com.JpaAllRelationship.JpaAllRelationship.dto.UserResponseDto;
import com.JpaAllRelationship.JpaAllRelationship.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/User")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/create")
    public ResponseEntity<UserResponseDto> createUser(@Valid @RequestBody UserRequestDto requestDto) {
       UserResponseDto responseDto = userService.createUser(requestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
//        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable Long id, @RequestBody UserRequestDto userRequestDto) {
        UserResponseDto userResponseDto = userService.updateUserById(id, userRequestDto);
        return ResponseEntity.ok(userResponseDto);
    }
}
