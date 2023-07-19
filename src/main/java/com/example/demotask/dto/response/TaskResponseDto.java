package com.example.demotask.dto.response;

import com.example.demotask.enums.Status;

import java.time.LocalDateTime;

public record TaskResponseDto(
        long id,
        String title,
        String description,
        Status status,
        LocalDateTime createdDate,
        LocalDateTime updatedDate,
        LocalDateTime completedDate
) {
}
