package com.gifter.app.auth.controller;

import com.gifter.app.auth.dto.AuthResponse;
import com.gifter.app.auth.dto.LoginDto;
import com.gifter.app.auth.dto.RegisterDto;
import com.gifter.app.auth.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
@CrossOrigin
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginDto loginDto) {
        return ResponseEntity.ok(authService.loginUseCase(loginDto));
    }

    @PostMapping("register")
    public ResponseEntity<AuthResponse> registerUseCase(@Valid @RequestBody RegisterDto userDto) {
        return ResponseEntity.ok(authService.registerUseCase(userDto));
    }

}