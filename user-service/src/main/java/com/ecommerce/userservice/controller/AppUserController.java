package com.ecommerce.userservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class AppUserController {

    @ResponseStatus(HttpStatus.CREATED)
    @GetMapping
    public String ok() {
        return "Hello";
    }

}
