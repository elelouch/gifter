package com.gifter.app.user.controller;

import com.gifter.app.gift.entity.Gift;
import com.gifter.app.user.dto.FindUserDto;
import com.gifter.app.user.dto.GifterUserDto;
import com.gifter.app.user.dto.UpdateUserDto;
import com.gifter.app.user.entity.GifterUser;
import com.gifter.app.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("current")
    public GifterUserDto getCurrentUser(Authentication authentication) {
        GifterUser user = (GifterUser) authentication.getPrincipal();
        return GifterUserDto.fromEntity(user);
    }

    @GetMapping
    public List<GifterUserDto> getUsers(@Valid @RequestBody FindUserDto dto) {
        return userService.findUsers(dto);
    }

    @DeleteMapping
    public void deleteUser() {
        userService.deleteUser();
    }

    @PutMapping
    public GifterUserDto updateUserDto(@Valid @RequestBody UpdateUserDto updateUserDto) {
        return userService.updateUser(updateUserDto);
    }
}
