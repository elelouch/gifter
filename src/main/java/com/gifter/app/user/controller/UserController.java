package com.gifter.app.user.controller;

import com.gifter.app.gift.entity.Gift;
import com.gifter.app.user.dto.GifterUserDto;
import com.gifter.app.gift.dto.UpdateGiftsDto;
import com.gifter.app.user.entity.GifterUser;
import com.gifter.app.user.service.UserService;
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

    @GetMapping("gifts")
    public List<Gift> getUserGifts(@PathVariable Long id) {
        return userService.getUserGifts();
    }

    @PostMapping("gifts")
    public void postUserGifts(UpdateGiftsDto giftsDto) {
        userService.updateGifts(giftsDto);
    }

}
