package com.gifter.app.user.service;

import com.gifter.app.user.entity.GifterUser;
import com.gifter.app.user.entity.dto.UserLoginDto;
import com.gifter.app.user.entity.dto.UserRegisterDto;

public interface UserService {
    GifterUser registerUseCase(UserRegisterDto registerDto);
    GifterUser loginUseCase(UserLoginDto registerDto);
    GifterUser getUserById(Long id);
}
