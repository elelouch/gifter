package com.gifter.app.auth.errors;

import com.gifter.app.auth.controller.AuthController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice(assignableTypes = {AuthController.class})
public class AuthControllerAdvice extends ResponseEntityExceptionHandler {
    @ExceptionHandler(UserAlreadyCreatedException.class)
    public ResponseEntity<Object> handleValidationException(UserAlreadyCreatedException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("status", "error");
        response.put("reason", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<Object> handleUserNotFound(UsernameNotFoundException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("status", "error");
        response.put("reason", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(IncorrectPasswordException.class)
    public ResponseEntity<Object> handleIncorrectPassword(IncorrectPasswordException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("status", "error");
        response.put("reason", ex.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }
}
