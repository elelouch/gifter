package com.gifter.app.auth.service;

import com.gifter.app.auth.dto.AuthResponse;
import com.gifter.app.auth.dto.LoginDto;
import com.gifter.app.auth.dto.RegisterDto;
import com.gifter.app.auth.errors.IncorrectPasswordException;
import com.gifter.app.auth.errors.UserAlreadyCreatedException;
import com.gifter.app.security.jwt.JwtService;
import com.gifter.app.user.entity.GifterUser;
import com.gifter.app.user.entity.Role;
import com.gifter.app.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public AuthResponse registerUseCase(RegisterDto userRegisterDto) {
        String email = userRegisterDto.getEmail();
        String username = userRegisterDto.getUsername();

        if (userRepository.findByEmailOrUsername(email, username).isPresent()) throw new UserAlreadyCreatedException();

        GifterUser user = new GifterUser();

        user.setPassword(encoder.encode(userRegisterDto.getPassword()));
        user.setUsername(userRegisterDto.getUsername());
        user.setEmail(userRegisterDto.getEmail());
        user.setFirstName(userRegisterDto.getFirstName());
        user.setLastName(userRegisterDto.getLastName());
        user.setRole(Role.USER);
        user.setEnabled(true);

        userRepository.save(user);

        return new AuthResponse(jwtService.generateToken(user));
    }

    public AuthResponse loginUseCase(LoginDto loginDto) {
        GifterUser user = userRepository.findByEmail(loginDto.getEmail()).orElseThrow(() -> new UsernameNotFoundException("Email does not exist"));
        if(!encoder.matches(loginDto.getPassword(), user.getPassword())) {
            throw new IncorrectPasswordException();
        }

        if(!user.isEnabled()) {
            throw new UsernameNotFoundException("User not found");
        }

        return new AuthResponse(jwtService.generateToken(user));
    }

}
