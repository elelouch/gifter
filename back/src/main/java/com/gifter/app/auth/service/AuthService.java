package com.gifter.app.auth.service;

import com.gifter.app.auth.dto.AuthResponse;
import com.gifter.app.auth.dto.LoginDto;
import com.gifter.app.user.dto.GifterUserDto;
import com.gifter.app.user.entity.GifterUser;
import com.gifter.app.auth.dto.RegisterDto;

public interface AuthService {
    GifterUserDto registerUseCase(RegisterDto registerDto);
    AuthResponse loginUseCase(LoginDto loginDto);
}
