package com.example.demotask.exception;

import com.example.demotask.dto.response.ApiResponse;
import com.example.demotask.model.BaseClass;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(NOT_ACCEPTABLE)
    public ResponseEntity<ApiResponse<Map<String, String>>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getFieldErrors().forEach(error -> {
            if (errors.containsKey(error.getField()))
                errors.put(error.getField(), String.format("%s, %s", errors.get(error.getField()), error.getDefaultMessage()));
            else
                errors.put(error.getField(), error.getDefaultMessage());
        });
        return new ResponseEntity<>(new ApiResponse<>("Validation Failed", errors, NOT_ACCEPTABLE.value()), NOT_ACCEPTABLE);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    @ResponseStatus(BAD_REQUEST)
    public ResponseEntity<ApiResponse<String>> handleUserAlreadyExistsException(UserAlreadyExistsException e) {
        return new ResponseEntity<>(new ApiResponse<>(e.getLocalizedMessage(), e.getMessage(), BAD_REQUEST.value()), BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public ResponseEntity<ApiResponse<String>> handleUserNotFoundException(UserNotFoundException e) {
        return new ResponseEntity<>(new ApiResponse<>(e.getLocalizedMessage(), e.getMessage(), BAD_REQUEST.value()), BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(BAD_REQUEST)
    public ResponseEntity<ApiResponse<String>> handleIllegalArgumentException(IllegalArgumentException e) {
        return new ResponseEntity<>(new ApiResponse<>(e.getLocalizedMessage(), e.getMessage(), BAD_REQUEST.value()), BAD_REQUEST);
    }

    @ExceptionHandler(IllegalStateException.class)
    @ResponseStatus(BAD_REQUEST)
    public ResponseEntity<ApiResponse<String>> handleIllegalStateException(IllegalStateException e) {
        return new ResponseEntity<>(new ApiResponse<>(e.getLocalizedMessage(), e.getMessage(), BAD_REQUEST.value()), BAD_REQUEST);
    }
}
