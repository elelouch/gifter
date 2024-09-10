package com.gifter.app.admin.controller;

import com.gifter.app.admin.service.AdminService;
import com.gifter.app.user.dto.GifterUserDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("admin")
@CrossOrigin
public class AdminController {
    @Autowired
    private AdminService adminService;

    @GetMapping("user")
    public List<GifterUserDto> getAllUser() {
        return adminService.getUsers();
    }

    @PostMapping("user")
    public GifterUserDto updateUser(@Valid @RequestBody GifterUserDto userDto) {
        return adminService.updateUser(userDto);
    }

    @DeleteMapping("user/{id}")
    public void deleteUser(@PathVariable Long id) {
        adminService.deleteUser(id);
    }

}
