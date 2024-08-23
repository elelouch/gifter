package com.gifter.app.user.controller;

import com.gifter.app.user.service.UserService;
import com.gifter.app.user.entity.GifterUser;
import com.gifter.app.user.entity.dto.UserRegisterDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("{id}")
    public GifterUser getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PostMapping("register")
    public GifterUser registerUseCase(@Valid @RequestBody UserRegisterDto userDto) {
        return this.userService.registerUseCase(userDto);
    }

}