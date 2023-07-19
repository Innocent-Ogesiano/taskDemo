package com.example.demotask.services.service_impl;

import com.example.demotask.dto.request.TaskRequestDto;
import com.example.demotask.dto.response.ApiResponse;
import com.example.demotask.dto.response.TaskResponseDto;
import com.example.demotask.model.Task;
import com.example.demotask.model.User;
import com.example.demotask.repository.TaskRepository;
import com.example.demotask.services.TaskService;
import com.example.demotask.util.MapperUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static org.springframework.http.HttpStatus.CREATED;

@Slf4j
@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final MapperUtil mapperUtil;

    @Override
    public ApiResponse<TaskResponseDto> createNewTask(TaskRequestDto requestDto, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null)
            throw new IllegalStateException("Kindly login to create a tak");

        Task task = mapperUtil.mapTaskRequestDtoToTask(requestDto);
        task.setUser(user);
        taskRepository.save(task);

        TaskResponseDto responseDto = mapperUtil.mapTaskToTaskResponseDto(task);
        return new ApiResponse<>("Task created successfully", responseDto, CREATED.value());
    }
}
