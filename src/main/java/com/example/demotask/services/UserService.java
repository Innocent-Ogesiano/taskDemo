package com.example.demotask.services;

import com.example.demotask.dto.request.UserLoginRequestDto;
import com.example.demotask.dto.request.UserSignupRequestDto;
import com.example.demotask.dto.response.ApiResponse;
import com.example.demotask.dto.response.UserResponseDto;
import jakarta.servlet.http.HttpServletRequest;

public interface UserService {
    ApiResponse<String> registerNewUser(UserSignupRequestDto requestDto);
    ApiResponse<UserResponseDto> login(UserLoginRequestDto loginRequestDto, HttpServletRequest request);
}
