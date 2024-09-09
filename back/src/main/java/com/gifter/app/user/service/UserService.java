package com.gifter.app.user.service;

import com.gifter.app.user.dto.FindUserDto;
import com.gifter.app.user.dto.GifterUserDto;
import com.gifter.app.user.dto.UpdateUserDto;

import java.util.List;

public interface UserService {
    GifterUserDto updateUser(UpdateUserDto userDto);

    void deleteUser();

    List<GifterUserDto> findUsers(FindUserDto dto);
}
