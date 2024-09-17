package com.gifter.app.error;

import com.gifter.app.auth.errors.IncorrectPasswordException;
import com.gifter.app.auth.errors.UserAlreadyCreatedException;
import com.gifter.app.request.follow.error.FollowRequestNotFound;
import com.gifter.app.user.error.UsernameAlreadyInUseException;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class AuthControllerAdvice extends ResponseEntityExceptionHandler {
    private static ResponseEntity<?> build(RuntimeException ex, HttpStatus status) {
        Map<String, String> response = new HashMap<>();
        response.put("status", "error");
        response.put("reason", ex.getMessage());
        return ResponseEntity.status(status).body(response);
    }

    @ExceptionHandler({UserAlreadyCreatedException.class, UsernameNotFoundException.class, FollowRequestNotFound.class, UsernameAlreadyInUseException.class})
    public ResponseEntity<?> handleErrors(RuntimeException ex) {
        return build(ex, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IncorrectPasswordException.class)
    public ResponseEntity<?> handleIncorrectPassword(IncorrectPasswordException ex) {
        return build(ex, HttpStatus.FORBIDDEN);
    }


    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<?> handleIncorrectPassword(ExpiredJwtException ex) {
        return build(ex, HttpStatus.UNAUTHORIZED);
    }




}
