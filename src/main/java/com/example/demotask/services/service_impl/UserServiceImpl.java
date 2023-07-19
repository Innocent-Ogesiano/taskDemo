package com.example.demotask.services.service_impl;

import com.example.demotask.dto.request.UserLoginRequestDto;
import com.example.demotask.dto.request.UserSignupRequestDto;
import com.example.demotask.dto.response.ApiResponse;
import com.example.demotask.dto.response.UserResponseDto;
import com.example.demotask.exception.UserAlreadyExistsException;
import com.example.demotask.exception.UserNotFoundException;
import com.example.demotask.model.User;
import com.example.demotask.repository.UserRepository;
import com.example.demotask.services.UserService;
import com.example.demotask.util.MapperUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final MapperUtil mapperUtil;

    @Override
    public ApiResponse<String> registerNewUser(UserSignupRequestDto requestDto) {
        if (userRepository.existsByEmail(requestDto.email()))
            throw new UserAlreadyExistsException("User with email " + requestDto.email() + " already exists");

        User user = mapperUtil.mapUserSignupDtoToUser(requestDto);
        userRepository.save(user);
        return new ApiResponse<>(
                "User registered successfully", "User registered successfully", CREATED.value()
        );
    }

    @Override
    public ApiResponse<UserResponseDto> login(UserLoginRequestDto loginRequestDto, HttpServletRequest request) {
        User user = userRepository.findByEmail(loginRequestDto.email())
                .orElseThrow(()->
                        new UserNotFoundException("User with email " + loginRequestDto.email() + " does not exist"));
        if (!user.getPassword().equals(loginRequestDto.password()))
            throw new IllegalArgumentException("Invalid credential");

        UserResponseDto responseDto = mapperUtil.mapUserToUserResponseDto(user);

        HttpSession session = request.getSession();
        session.setAttribute("user", user);

        return new ApiResponse<>("Login successful", responseDto, OK.value());
    }
}
