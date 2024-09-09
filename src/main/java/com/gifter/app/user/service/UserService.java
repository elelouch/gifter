package com.gifter.app.user.service;

import com.gifter.app.user.dto.GifterUserDto;
import com.gifter.app.user.dto.UpdateUserDto;

public interface UserService {
    GifterUserDto updateUser(UpdateUserDto userDto);
    void deleteUser();
}
