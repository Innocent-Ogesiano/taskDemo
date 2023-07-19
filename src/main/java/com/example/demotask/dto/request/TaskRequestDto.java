package com.example.demotask.dto.request;

import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;

/**
 * A DTO for the {@link com.example.demotask.model.Task} entity
 */
public record TaskRequestDto(
        @NotBlank(message = "Please enter the title")
        String title,
        @NotBlank(message = "Please enter the description")
        String description
) implements Serializable {
}