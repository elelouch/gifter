package com.gifter.app.user.service;

import com.gifter.app.user.entity.GifterUser;
import com.gifter.app.user.entity.dto.UserLoginDto;
import com.gifter.app.user.entity.dto.UserRegisterDto;
import com.gifter.app.user.errors.UserAlreadyCreatedException;
import com.gifter.app.user.errors.UserNotFoundException;
import com.gifter.app.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public GifterUser loginUseCase(UserLoginDto userLoginDto) {
        String email = userLoginDto.getEmail();
        GifterUser user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UserNotFoundException();
        }

        return userRepository.save(user);
    }

    @Override
    public GifterUser registerUseCase(UserRegisterDto userRegisterDto) {
        String email = userRegisterDto.getEmail();
        String username = userRegisterDto.getUsername();
        GifterUser user = new GifterUser();

        if(!userRepository.findByEmailOrUsername(email, username).isEmpty()) {
            throw new UserAlreadyCreatedException();
        }

        user.setEmail(email);
        user.setPassword(userRegisterDto.getPassword());
        user.setUsername(username);

        return userRepository.save(user);
    }

  @Override
  public GifterUser getUserById(Long id) {
      return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
  }
}
