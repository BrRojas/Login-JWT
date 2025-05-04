package com.brian.jwtmock.controller;

import com.brian.jwtmock.model.LoginRequestDTO;
import com.brian.jwtmock.model.AuthResponse;
import com.brian.jwtmock.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class LoginController {

    private final AuthService authService;

    public LoginController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequestDTO loginRequest) {
        AuthResponse token = authService.login(loginRequest);
        return ResponseEntity.ok(token);
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody LoginRequestDTO registerRequest) {
        AuthResponse token = authService.register(registerRequest);
        return ResponseEntity.ok(token);
    }
}
