package com.gifter.app.admin.service;

import com.gifter.app.user.dto.GifterUserDto;
import com.gifter.app.user.entity.GifterUser;
import com.gifter.app.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public GifterUserDto updateUser(GifterUserDto dto) {
        GifterUser user = userRepository.findById(dto.getId()).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        user.setLastName(dto.getLastName());
        user.setFirstName(dto.getFirstName());
        user.setUsername(dto.getUsername());
        user.setEnabled(dto.isEnabled());
        user.setRole(dto.getRole());
        user.setPassword(dto.getPassword());
        return GifterUserDto.fromEntity(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}

