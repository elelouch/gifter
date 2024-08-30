package com.gifter.app.user.service;

import com.gifter.app.user.entity.GifterUser;
import com.gifter.app.user.entity.dto.UserLoginDto;
import com.gifter.app.user.entity.dto.UserRegisterDto;

import java.util.Optional;

public interface UserService {
    GifterUser registerUseCase(UserRegisterDto registerDto);
    Optional<GifterUser> getUserById(Long id);
}
