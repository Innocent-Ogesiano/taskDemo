package com.example.demotask.dto.response;

public record ApiResponse<T>(String message, T data, int status) {
}
