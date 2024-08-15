package com.gifter.app.user.controller;

import com.gifter.app.user.errors.ValidationException;
import com.gifter.app.user.service.UserService;
import com.gifter.app.user.entity.GifterUser;
import com.gifter.app.user.entity.register.UserRegisterDto;
import com.gifter.app.user.entity.register.UserRegisterValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRegisterValidator validator;

    @GetMapping("{id}")
    public GifterUser getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PostMapping
    public GifterUser postUser(@Valid @RequestBody UserRegisterDto userDto) {
        return this.userService.registerUseCase(userDto);
    }

}