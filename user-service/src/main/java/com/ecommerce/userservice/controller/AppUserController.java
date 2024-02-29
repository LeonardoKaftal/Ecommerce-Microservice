package com.ecommerce.userservice.controller;

import com.ecommerce.userservice.dto.RegisterRequest;
import com.ecommerce.userservice.dto.RegisterResponse;
import com.ecommerce.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class AppUserController {

    private final UserService userService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public RegisterResponse registerUser(@RequestBody RegisterRequest registerRequest) {

       return userService.registerUser(registerRequest);
    }

}
