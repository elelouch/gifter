package com.gifter.app.user.entity.register;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.regex.Pattern;

@Component
public class UserRegisterValidator implements Validator {
    private final int MAX_LENGTH = 255;
    Pattern emailPattern = Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
    Pattern passwordPattern = Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$");

    @Override
    public boolean supports(Class<?> clazz) {
        return UserRegisterDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserRegisterDto user = (UserRegisterDto) target;
        String email = user.getEmail();
        String password = user.getPassword();
        boolean emailIsValid = emailPattern.matcher(email).matches();
        boolean passwordIsValid = passwordPattern.matcher(password).matches();
        
        if (!emailIsValid) {
            errors.rejectValue("email", "email.not.valid");
        }
        if (email.length() > MAX_LENGTH) {
            errors.rejectValue("email", "email.too.long");
        }

        if (!passwordIsValid) {
            errors.rejectValue("password", "password.not.valid");
        }

        if (password.length() > MAX_LENGTH) {
            errors.rejectValue("password", "password.too.long");
        }

    }

    @Override
    public Errors validateObject(Object target) {
        return Validator.super.validateObject(target);
    }
}
