package com.gifter.app.user.service;

import com.gifter.app.user.dto.FindUserDto;
import com.gifter.app.user.dto.FollowersDto;
import com.gifter.app.user.dto.GifterUserDto;
import com.gifter.app.user.dto.UpdateUserDto;
import com.gifter.app.user.entity.GifterUser;
import com.gifter.app.user.error.EmailAlreadyInUseException;
import com.gifter.app.user.error.UsernameAlreadyInUseException;
import com.gifter.app.user.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;

    private GifterUser getCurrentUser() {
        return (GifterUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @Override
    public GifterUserDto updateUser(UpdateUserDto userDto) {
        GifterUser current = getCurrentUser();
        userRepository.findByEmailOrUsername(userDto.getEmail(), userDto.getUsername()).ifPresent(user -> {
            if (user.getEmail().equals(current.getEmail())) {
                throw new EmailAlreadyInUseException();
            }
            if (user.getUsername().equals(current.getUsername())) {
                throw new UsernameAlreadyInUseException();
            }
        });
        current.setEmail(userDto.getEmail());
        current.setPassword(passwordEncoder.encode(userDto.getPassword()));
        current.setFirstName(userDto.getFirstName());
        current.setLastName(userDto.getLastName());
        current.setUsername(userDto.getUsername());
        return GifterUserDto.fromEntity(current);
    }

    @Override
    public void deleteUser() {
        GifterUser user = getCurrentUser();
        user.setEnabled(false);
        userRepository.save(user);
    }

    @Override
    public List<GifterUserDto> findUsers(FindUserDto dto) {
        return GifterUserDto.fromEntity(userRepository.getByLikeUsername(dto.getUsername()));
    }

    @Override
    public FollowersDto getFollowing() {
        return userRepository.findById(getCurrentUser().getId()).map(user -> {
            List<GifterUserDto> list = GifterUserDto.fromEntity(List.copyOf(user.getFollowing()));
            FollowersDto dto = new FollowersDto();
            dto.setList(list);
            return dto;
        }).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Override
    public FollowersDto getFollowers() {
        return userRepository.findById(getCurrentUser().getId()).map(user -> {
            List<GifterUserDto> list = GifterUserDto.fromEntity(List.copyOf(user.getFollowers()));
            FollowersDto dto = new FollowersDto();
            dto.setList(list);
            return dto;
        }).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Override
    public GifterUserDto findByUsername(String username) {
        return userRepository.findByUsername(username).map(GifterUserDto::fromEntity)
                .orElseThrow(() -> new UsernameNotFoundException("User does not exists"));
    }
}
