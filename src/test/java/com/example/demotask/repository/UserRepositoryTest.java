package com.example.demotask.repository;

import com.example.demotask.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

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
    void shouldSaveNewUserToDb() {
        User savedUser = userRepository.save(user);
        assertThat(savedUser).isNotNull().isEqualTo(user);
    }

    @Test
    void existsByEmail() {
        assertFalse(userRepository.existsByEmail(user.getEmail()));
//        boolean result = userRepository.existsByEmail(user.getEmail());
//        assertThat(result).isFalse();
        userRepository.save(user);

        assertTrue(userRepository.existsByEmail(user.getEmail()));
    }

    @Test
    void findByEmail() {
        userRepository.save(user);
        Optional<User> optionalUser = userRepository.findByEmail(user.getEmail());
        assertThat(optionalUser).isPresent();
        assertThat(optionalUser.get()).isEqualTo(user);
    }
}