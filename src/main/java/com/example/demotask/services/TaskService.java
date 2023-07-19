package com.example.demotask.services;

import com.example.demotask.dto.request.TaskRequestDto;
import com.example.demotask.dto.response.ApiResponse;
import com.example.demotask.dto.response.TaskResponseDto;
import jakarta.servlet.http.HttpServletRequest;

public interface TaskService {
    ApiResponse<TaskResponseDto> createNewTask(TaskRequestDto requestDto, HttpServletRequest request);
}
