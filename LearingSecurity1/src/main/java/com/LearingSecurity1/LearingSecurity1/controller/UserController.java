package com.LearingSecurity1.LearingSecurity1.controller;

import com.LearingSecurity1.LearingSecurity1.Dto.UserRegisterRequestDto;
import com.LearingSecurity1.LearingSecurity1.Dto.UserRegisterResponseDto;
import com.LearingSecurity1.LearingSecurity1.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
//    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private final AuthService authService;

//    public UserController(AuthService authService) {
//        this.authService = authService;
//    }

    @GetMapping("/hello")
    public String sayHello(Authentication authentication) {
        return "Hello, you are logged in as : " + authentication.getName();
//                + " ,Principle : " + authentication.getPrincipal()
//                + " ,Credential :  " + authentication.getCredentials()
//                + " ,Authorization : " + authentication.getAuthorities()
//                + " ,Details : " + authentication.getDetails();
    }

    @PostMapping("/register")
    public ResponseEntity<UserRegisterResponseDto> register(@RequestBody UserRegisterRequestDto requestDto) {
        UserRegisterResponseDto userRegisterResponseDto =
                authService.register(requestDto);

        return ResponseEntity.ok(userRegisterResponseDto);
    }

//    @GetMapping("/token")
//    public CsrfToken getToken(CsrfToken csrfToken) {
//        return csrfToken;
//    }

//    @PostMapping("/login")
//    public ResponseEntity<Boolean> login(@RequestBody UserRegisterRequestDto registerRequestDto) {
//        Boolean loggedIn = authService.login(registerRequestDto);
//        return ResponseEntity.ok(loggedIn);
//    }



//    @GetMapping("/user")
//    public String getUser() {
//        System.out.println(passwordEncoder.encode("secret123"));
//        System.out.println(passwordEncoder.encode("secret123"));
//
//        System.out.println(passwordEncoder.matches("secret123",
//                "$2a$10$GfCHqQyWoLLQhK3blfu6Suapj2gJHnIAyFW/AMf4KJ4uFBJYEXHX6"));
//        return "Hello from User1";
//    }
}
