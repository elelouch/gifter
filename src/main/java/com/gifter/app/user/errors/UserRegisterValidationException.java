package com.gifter.app.user.errors;

import lombok.Data;
import lombok.Getter;
import org.springframework.validation.Errors;

@Getter
public class UserRegisterValidationException extends RuntimeException{
    private Errors errors;

    public UserRegisterValidationException() {
        super("Error validating a field");
    }

    public UserRegisterValidationException(Errors errors) {
        super();
        this.errors = errors;
    }
}
