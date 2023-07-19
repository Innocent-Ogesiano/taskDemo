package com.example.demotask.exception;

import jakarta.validation.constraints.NotBlank;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(@NotBlank String s) {
        super(s);
    }
}
