package com.ecommerce.userservice.service;

import com.ecommerce.userservice.configuration.MyPasswordEncoder;
import com.ecommerce.userservice.dto.RegisterRequest;
import com.ecommerce.userservice.dto.RegisterResponse;
import com.ecommerce.userservice.entity.AppUser;
import com.ecommerce.userservice.exception.UsernameOrEmailAlreadyTakenException;
import com.ecommerce.userservice.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
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

    @Test
    void shouldThrowAnExceptionRegisteringTwiceAUser() {
        String rawPassword = "a1234";
        Mockito.when(passwordEncoder.encode(rawPassword))
                .thenReturn(rawPassword);

        RegisterRequest registerRequest = RegisterRequest
                .builder()
                .username("luca")
                .email("luca@a.com")
                .password(passwordEncoder.encode(rawPassword))
                .build();

        RegisterResponse expectedResponse = RegisterResponse
                .builder()
                .username(registerRequest.username())
                .email(registerRequest.email())
                .build();

        AppUser appUser = AppUser
                .builder()
                .email(registerRequest.email())
                .username(registerRequest.username())
                .password(passwordEncoder.encode(registerRequest.password()))
                .build();


        underTest = new UserService(userRepository, passwordEncoder);


        RegisterResponse registeredUser = underTest.registerUser(registerRequest);
        verify(userRepository).findAppUserByEmail(registerRequest.email());
        assertThat(registeredUser).isEqualTo(expectedResponse);

        Mockito.when(userRepository.findAppUserByEmail(appUser.getEmail()))
                .thenReturn(Optional.of(appUser));
        underTest = new UserService(userRepository,passwordEncoder);

        assertThatThrownBy(()-> underTest
                .registerUser(registerRequest))
                .isInstanceOf(UsernameOrEmailAlreadyTakenException.class)
                .hasMessage("Email " + registerRequest.email() + " is already taken");

        appUser.setEmail("a@a");
        Mockito.when(userRepository.findAppUserByUsername(appUser.getUsername()))
                .thenReturn(Optional.of(appUser));

        underTest = new UserService(userRepository,passwordEncoder);

        RegisterRequest registerRequest2 = RegisterRequest
                .builder()
                .username(appUser.getUsername())
                .email(appUser.getEmail())
                .password(passwordEncoder.encode(rawPassword))
                .build();

        assertThatThrownBy(()-> underTest
                .registerUser(registerRequest2))
                .isInstanceOf(UsernameOrEmailAlreadyTakenException.class)
                .hasMessage("Username " + registerRequest.username() + " is already taken");

    }
}