package com.gifter.app.user.controller;

import com.gifter.app.request.follow.FollowRequestDto;
import com.gifter.app.user.dto.GifterUserDto;
import com.gifter.app.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public List<GifterUserDto> getUsers() {
        return userService.getUsers();
    }

    @PostMapping
    public void post

    @PostMapping("follow")
    public void createFollowRequest(@Valid @RequestBody FollowRequestDto followRequest) {
        userService.createFollowRequest(followRequest);
    }

    @PostMapping("follow/{id}")
    public void removeFollowRequest (@PathVariable Long id) {
        userService.removeFollowRequest(id);
    }

}
