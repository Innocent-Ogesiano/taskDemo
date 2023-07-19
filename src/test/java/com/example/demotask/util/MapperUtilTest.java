package com.example.demotask.util;

import com.example.demotask.dto.request.UserSignupRequestDto;
import com.example.demotask.model.User;
import org.hibernate.mapping.UserDefinedType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class MapperUtilTest {
    @InjectMocks
    private MapperUtil mapperUtil;

    @Test
    void mapUserSignupDtoToUser() {
        UserSignupRequestDto requestDto = new UserSignupRequestDto(
                "username", "password", "email");
        User user = mapperUtil.mapUserSignupDtoToUser(requestDto);

        assertThat(user).isNotNull();
        assertThat(user.getEmail()).isEqualTo(requestDto.email());
        assertThat(user.getUsername()).isEqualTo(requestDto.username());
        assertThat(user.getPassword()).isEqualTo(requestDto.password());
    }

    @Test
    void mapUserToUserResponseDto() {
    }

    @Test
    void mapTaskRequestDtoToTask() {
    }

    @Test
    void mapTaskToTaskResponseDto() {
    }
}