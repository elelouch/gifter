package com.gifter.app.user.errors;

import com.gifter.app.user.controller.UserController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice(assignableTypes = {UserController.class})
public class UserControllerAdvice extends ResponseEntityExceptionHandler {
    @ExceptionHandler(UserAlreadyCreatedException.class)
    public ResponseEntity<Object> handleValidationException(UserAlreadyCreatedException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("status", "error");
        response.put("reason", "user already created");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> handleUserNotFound(UserNotFoundException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("status", "error");
        response.put("reason", "user not found");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
