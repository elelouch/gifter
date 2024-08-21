package com.gifter.app.user.service;

import com.gifter.app.user.entity.GifterUser;
import com.gifter.app.user.entity.register.UserRegisterDto;
import com.gifter.app.user.entity.register.UserRegisterValidator;
import com.gifter.app.user.errors.UserRegisterValidationException;
import com.gifter.app.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRegisterValidator userRegisterValidator;

    @Override
    public GifterUser registerUseCase(UserRegisterDto userRegisterDto) {
        Errors errors = userRegisterValidator.validateObject(userRegisterDto);

        if(errors.hasErrors()) {
            throw new UserRegisterValidationException(errors);
        }

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
      return userRepository.findById(id).orElseThrow(UserRegisterValidationException::new);
  }
}
