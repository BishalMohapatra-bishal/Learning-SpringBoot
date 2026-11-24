package com.LearingSecurity1.LearingSecurity1.service;

import com.LearingSecurity1.LearingSecurity1.Dto.UserRegisterRequestDto;
import com.LearingSecurity1.LearingSecurity1.Dto.UserRegisterResponseDto;
import com.LearingSecurity1.LearingSecurity1.entity.Role;
import com.LearingSecurity1.LearingSecurity1.entity.User;
import com.LearingSecurity1.LearingSecurity1.repository.RoleRepository;
import com.LearingSecurity1.LearingSecurity1.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserRegisterResponseDto register(UserRegisterRequestDto registerRequestDto) {
        User user = new User(); //Entity

//The name come from the clint site through requestDto is set to the user entity to save in the database
        user.setUsername(registerRequestDto.getUsername());

//The incoming password come from the clint site through requestDto is converted to encoded format so that it will
//store in the database(Because storing the raw password to the database can lead to password leak)
        String encodedpassword =
                passwordEncoder.encode(registerRequestDto.getPassword());

//Set the password to the userEntity
        user.setPassword(encodedpassword);

        user.setEnabled(true);

        Role role = roleRepository.findByName("ROLE_USER").get();

        user.getRoles().add(role);

//Finally store the entity in the database
        userRepository.save(user);

//Create a responseDto object to set the value to it that case from
// clint site so it return back to the clint a response
        UserRegisterResponseDto responseDto = new UserRegisterResponseDto();


//Set the user name and a message to the responseDto and return it to the caller controller method handler
        responseDto.setUsername(user.getUsername());
        responseDto.setMessage("User Saved Successfully");
        return responseDto;

    }


//    public Boolean login(UserRegisterRequestDto registerRequestDto) {
//        Optional<User> userOptional = userRepository.findByUsername(registerRequestDto.getUsername());
//
//        User user = userOptional.get();
//        String encodedPassword = user.getPassword();
//
//        return passwordEncoder.matches(registerRequestDto.getPassword(), encodedPassword);
//    }
}
