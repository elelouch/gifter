package com.gifter.app.admin.controller;

import com.gifter.app.user.dto.GifterUserDto;
import com.gifter.app.admin.service.AdminService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @PostMapping("user")
    public GifterUserDto updateUser(@Valid @RequestBody GifterUserDto userDto) {
        return adminService.updateUser(userDto);
    }

    @DeleteMapping("user/{id}")
    public void deleteUser(@PathVariable Long id) {
        adminService.deleteUser(id);
    }

}
