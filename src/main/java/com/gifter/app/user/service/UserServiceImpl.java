package com.gifter.app.user.service;

import com.gifter.app.user.entity.GifterUser;
import com.gifter.app.user.entity.register.UserRegisterDto;
import com.gifter.app.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public GifterUser registerUseCase(UserRegisterDto userRegisterDto) {
        String email = userRegisterDto.getEmail();
        String username = userRegisterDto.getUsername();
        String password = userRegisterDto.getPassword();
        GifterUser user = new GifterUser();

        if(!userRepository.findByEmailOrPassword(email, username).isEmpty()) {
            return user;
        }

        user.setEmail(email);
        user.setPassword(password);
        user.setUsername(username);

        return userRepository.save(user);
    }

  @Override
  public GifterUser getUserById(Long id) {
      return userRepository.findById(id).orElseThrow();
  }
}
