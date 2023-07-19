package com.example.demotask.controller;

import com.example.demotask.dto.request.UserLoginRequestDto;
import com.example.demotask.dto.request.UserSignupRequestDto;
import com.example.demotask.dto.response.ApiResponse;
import com.example.demotask.dto.response.UserResponseDto;
import com.example.demotask.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<String>> registerNewUser(@Valid @RequestBody UserSignupRequestDto requestDto) {
        return new ResponseEntity<>(userService.registerNewUser(requestDto), CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<UserResponseDto>> loginUser(@Valid @RequestBody UserLoginRequestDto loginRequestDto, HttpServletRequest request) {
        return new ResponseEntity<>(userService.login(loginRequestDto, request), OK);
    }
}
