package com.example.demotask.dto.request;

import jakarta.validation.constraints.NotBlank;

public record UserLoginRequestDto(
        @NotBlank
        String email,
        @NotBlank
        String password
) {
}
