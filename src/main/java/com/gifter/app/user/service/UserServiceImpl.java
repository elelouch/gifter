package com.gifter.app.user.service;

import com.gifter.app.user.dto.FindUserDto;
import com.gifter.app.user.dto.GifterUserDto;
import com.gifter.app.user.dto.UpdateUserDto;
import com.gifter.app.user.entity.GifterUser;
import com.gifter.app.user.error.EmailAlreadyInUse;
import com.gifter.app.user.error.UsernameAlreadyInUse;
import com.gifter.app.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
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
        String currentUserEmail = current.getEmail();
        String currentUserUsername = current.getUsername();
        Optional<GifterUser> optionalUser = userRepository.findByEmailOrUsername(userDto.getEmail(), userDto.getUsername());

        if (optionalUser.isPresent()) {
            GifterUser user = optionalUser.get();
            if (user.getEmail().equals(currentUserEmail)) {
                throw new EmailAlreadyInUse();
            }
            if (user.getUsername().equals(currentUserUsername)) {
                throw new UsernameAlreadyInUse();
            }
        }

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
        List<GifterUser> users = userRepository.findByUsernameLike(dto.getUsername());
        return GifterUserDto.fromEntity(users);
    }

}
