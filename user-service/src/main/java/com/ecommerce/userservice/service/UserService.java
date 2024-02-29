package com.ecommerce.userservice.service;

import com.ecommerce.userservice.configuration.MyPasswordEncoder;
import com.ecommerce.userservice.dto.RegisterRequest;
import com.ecommerce.userservice.dto.RegisterResponse;
import com.ecommerce.userservice.entity.AppUser;
import com.ecommerce.userservice.exception.UsernameOrEmailAlreadyTakenException;
import com.ecommerce.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final MyPasswordEncoder passwordEncoder;
    
    public RegisterResponse registerUser(@NonNull RegisterRequest registerRequest) {
        boolean isEmailTaken = userRepository.findAppUserByEmail(registerRequest.email()).isPresent();
        boolean isUsernameTaken = userRepository.findAppUserByUsername(registerRequest.username()).isPresent();

        if (isEmailTaken) throw new UsernameOrEmailAlreadyTakenException("Email " + registerRequest.email() + " is already taken");
        if (isUsernameTaken) throw new UsernameOrEmailAlreadyTakenException("Username " + registerRequest.username() + " is already taken");

        AppUser appUser = AppUser
                .builder()
                .email(registerRequest.email())
                .username(registerRequest.username())
                .password(passwordEncoder.encode(registerRequest.password()))
                .build();


        log.info("Registering user {} at date {}", appUser.getUsername(), LocalDate.now());

        userRepository.save(appUser);
        return RegisterResponse
                .builder()
                .email(appUser.getEmail())
                .username(appUser.getUsername())
                .build();
    }

    /*public AppUser searchByEmail(String email) {
        return userRepository
                .findAppUserByEmail(email)
                .orElseThrow(() ->
                        new UserNotFoundException(String.format("Username %s has not been found searching by email %s",
                                registerRequest.username(),registerRequest.email())));
    }*/
}
