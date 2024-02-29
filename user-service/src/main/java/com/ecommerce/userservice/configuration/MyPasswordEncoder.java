package com.ecommerce.userservice.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class MyPasswordEncoder {

    public final static BCryptPasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();
    public String encode(String password) {
        return PASSWORD_ENCODER.encode(password);
    }

}
