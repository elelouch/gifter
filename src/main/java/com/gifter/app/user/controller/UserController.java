package com.gifter.app.user.controller;

import com.gifter.app.gift.entity.Gift;
import com.gifter.app.request.follow.FollowRequestDto;
import com.gifter.app.user.dto.GifterUserDto;
import com.gifter.app.user.entity.GifterUser;
import com.gifter.app.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public List<GifterUserDto> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("current")
    public GifterUserDto getCurrentUser(Authentication authentication) {
        GifterUser user = (GifterUser) authentication.getPrincipal();
        return GifterUserDto.fromEntity(user);
    }

    @GetMapping("{id}/gifts")
    public Set<Gift> getUserGifts(@PathVariable Long id) {
        return userService.getUserGifts(id);
    }

    @PostMapping("{id}/gifts")
    public void postUserGifts(@PathVariable Long id, @RequestBody Set<Gift> gifts) {
        userService.updateGifts(updateGiftsDto);
    }

    @PostMapping("follow")
    public void createFollowRequest(@Valid @RequestBody FollowRequestDto followRequest) {
        userService.createFollowRequest(followRequest);
    }

    @PostMapping("follow/{id}")
    public void removeFollowRequest(@PathVariable Long id) {
        userService.removeFollowRequest(id);
    }
}
