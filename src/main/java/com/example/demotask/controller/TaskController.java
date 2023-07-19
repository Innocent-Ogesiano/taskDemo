package com.example.demotask.controller;

import com.example.demotask.dto.request.TaskRequestDto;
import com.example.demotask.dto.response.ApiResponse;
import com.example.demotask.dto.response.TaskResponseDto;
import com.example.demotask.services.TaskService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/task")
public class TaskController {
    private final TaskService taskService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<TaskResponseDto>> createNewTask(@Valid @RequestBody TaskRequestDto requestDto, HttpServletRequest request) {
        return new ResponseEntity<>(taskService.createNewTask(requestDto, request), CREATED);
    }
}
