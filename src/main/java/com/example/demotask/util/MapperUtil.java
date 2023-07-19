package com.example.demotask.util;

import com.example.demotask.dto.request.TaskRequestDto;
import com.example.demotask.dto.request.UserSignupRequestDto;
import com.example.demotask.dto.response.TaskResponseDto;
import com.example.demotask.dto.response.UserResponseDto;
import com.example.demotask.model.Task;
import com.example.demotask.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class MapperUtil {

    public User mapUserSignupDtoToUser(UserSignupRequestDto requestDto) {
        User user = new User();
        BeanUtils.copyProperties(requestDto, user);
        return user;
    }

    public UserResponseDto mapUserToUserResponseDto(User user) {
        return new UserResponseDto(user.getId(), user.getUsername(), user.getEmail());
    }

    public Task mapTaskRequestDtoToTask(TaskRequestDto requestDto) {
        Task task = new Task();
        BeanUtils.copyProperties(requestDto, task);
        return task;
    }

    public TaskResponseDto mapTaskToTaskResponseDto(Task task) {
        return new TaskResponseDto(
                task.getId(), task.getTitle(), task.getDescription(), task.getStatus(),
                task.getCreatedDate(), task.getUpdatedDate(),task.getCompletedAt()
        );
    }
}
