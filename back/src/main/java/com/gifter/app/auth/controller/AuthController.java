package com.gifter.app.auth.controller;

import com.gifter.app.auth.dto.AuthResponse;
import com.gifter.app.auth.dto.LoginDto;
import com.gifter.app.auth.dto.RegisterDto;
import com.gifter.app.auth.service.AuthService;
import com.gifter.app.user.dto.GifterUserDto;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
@CrossOrigin
public class AuthController {
    Logger logger = LoggerFactory.getLogger(AuthController.class);
    @Autowired
    private AuthService authService;

    @PostMapping("login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginDto loginDto) {
        logger.info("login request received");
        return ResponseEntity.ok(authService.loginUseCase(loginDto));
    }

    @PostMapping("register")
    public GifterUserDto registerUseCase(@Valid @RequestBody RegisterDto userDto) {
        logger.info("register request received");
        return authService.registerUseCase(userDto);
    }

}