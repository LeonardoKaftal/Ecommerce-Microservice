package com.ecommerce.userservice.service;

import com.ecommerce.userservice.configuration.MyPasswordEncoder;
import com.ecommerce.userservice.dto.RegisterRequest;
import com.ecommerce.userservice.dto.RegisterResponse;
import com.ecommerce.userservice.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    UserService underTest;
    @Mock
    UserRepository userRepository;
    @Mock
    MyPasswordEncoder passwordEncoder;

    @Test
    void shouldRegisterUser() {
        String rawPassword = "a1234";
        Mockito.when(passwordEncoder.encode(rawPassword))
                .thenReturn(rawPassword);

        RegisterRequest registerRequest = RegisterRequest
                .builder()
                .username("luca")
                .email("luca@a.com")
                .password(passwordEncoder.encode(rawPassword))
                .build();



        underTest = new UserService(userRepository, passwordEncoder);
        RegisterResponse expectedResponse = RegisterResponse
                .builder()
                .username(registerRequest.username())
                .email(registerRequest.email())
                .build();

        RegisterResponse registeredUser = underTest.registerUser(registerRequest);
        verify(userRepository).findAppUserByEmail(registerRequest.email());
        assertThat(registeredUser).isEqualTo(expectedResponse);

    }
}