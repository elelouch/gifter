package com.gifter.app.user.errors;

import com.gifter.app.user.controller.UserController;
import com.gifter.app.user.errors.dto.GenericErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice(assignableTypes = {UserController.class})
public class UserExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(UserRegisterValidationException.class)
    public ResponseEntity<GenericErrorDto> handleValidationException(UserRegisterValidationException ex) {
        Map<String, Object> reasonsMap = new HashMap<>();
        Errors errors = ex.getErrors();
        for(FieldError error: errors.getFieldErrors()){
            String field = error.getField();
            reasonsMap.put(field, errors.getFieldValue(field));
        }

        GenericErrorDto errorDto = new GenericErrorDto();
        errorDto.setReasons(reasonsMap);
        errorDto.setStatusMessage("error");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDto);
    }
}
