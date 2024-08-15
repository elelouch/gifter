package com.gifter.app.user.errors;

import org.springframework.validation.Errors;

public class ValidationException extends RuntimeException{
    public ValidationException(Errors errors) {
        super("pedrongas");
    }
}
