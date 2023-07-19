package com.example.demotask.services.service_impl;

import com.example.demotask.dto.request.UserLoginRequestDto;
import com.example.demotask.dto.request.UserSignupRequestDto;
import com.example.demotask.dto.response.ApiResponse;
import com.example.demotask.dto.response.UserResponseDto;
import com.example.demotask.exception.UserAlreadyExistsException;
import com.example.demotask.model.User;
import com.example.demotask.repository.UserRepository;
import com.example.demotask.util.MapperUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private MapperUtil mapperUtil;
    @InjectMocks
    private UserServiceImpl userService;

    private User user;

    @BeforeEach
    void setUp() {
        user = User.builder()
                .username("username")
                .email("email")
                .password("password")
                .build();
    }

    @Test
    void registerNewUser() {
        UserSignupRequestDto requestDto = new UserSignupRequestDto(
                "username", "password", "email");

        when(userRepository.existsByEmail(requestDto.email())).thenReturn(false);
        when(mapperUtil.mapUserSignupDtoToUser(requestDto)).thenReturn(user);

        String message = "User registered successfully";

        ApiResponse<String> response = userService.registerNewUser(requestDto);
        assertThat(response).isNotNull();
        assertThat(response.message()).isEqualTo(message);
        assertThat(response.data()).isEqualTo(message);
    }

    @Test
    void shouldThrowAnExceptionWhenEmailAlreadyExists() {
        UserSignupRequestDto requestDto = new UserSignupRequestDto(
                "username", "password", "email");

        when(userRepository.existsByEmail(requestDto.email())).thenReturn(true);

        assertThrows(UserAlreadyExistsException.class, ()-> userService.registerNewUser(requestDto));
    }

    @Test
    void login() {
        UserLoginRequestDto loginRequestDto = new UserLoginRequestDto("email", "password");
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpSession session = mock(HttpSession.class);
        UserResponseDto responseDto = new UserResponseDto(1L, user.getUsername(), user.getEmail());

        when(userRepository.findByEmail(loginRequestDto.email())).thenReturn(Optional.of(user));
        when(request.getSession()).thenReturn(session);
        when(mapperUtil.mapUserToUserResponseDto(any(User.class))).thenReturn(responseDto);

        ApiResponse<UserResponseDto> response = userService.login(loginRequestDto, request);
        assertThat(response).isNotNull();
        assertThat(response.data()).isNotNull();
        assertThat(response.data().email()).isEqualTo(user.getEmail());
    }
}