package com.gifter.app.auth.controller;

import com.gifter.app.auth.dto.AuthResponse;
import com.gifter.app.auth.dto.LoginDto;
import com.gifter.app.auth.service.AuthService;
import com.gifter.app.user.entity.GifterUser;
import com.gifter.app.auth.dto.RegisterDto;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @GetMapping("login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginDto loginDto) {
        return ResponseEntity.ok(authService.loginUseCase(loginDto));
    }

    @PostMapping("register")
    public ResponseEntity<AuthResponse> registerUseCase(@Valid @RequestBody RegisterDto userDto) {
        return ResponseEntity.ok(authService.registerUseCase(userDto));
    }

    @GetMapping("test")
    public String test() {
        return "hello world";
    }

}