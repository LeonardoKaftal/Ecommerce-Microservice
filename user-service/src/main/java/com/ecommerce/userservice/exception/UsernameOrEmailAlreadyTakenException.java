package com.ecommerce.userservice.exception;

public class UsernameOrEmailAlreadyTakenException extends RuntimeException {

    public UsernameOrEmailAlreadyTakenException(String message) {
        super(message);
    }
}
