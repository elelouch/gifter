package com.gifter.app.user.service;

import com.gifter.app.user.entity.GifterUser;
import com.gifter.app.user.entity.register.UserRegisterDto;

public interface UserService {
    GifterUser registerUseCase(UserRegisterDto registerDto);
    GifterUser getUserById(Long id);
}
