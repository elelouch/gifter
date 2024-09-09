package com.gifter.app.admin.service;

import com.gifter.app.user.dto.GifterUserDto;

public interface AdminService {
    GifterUserDto updateUser(GifterUserDto userDto);
    void deleteUser(Long id);
}
