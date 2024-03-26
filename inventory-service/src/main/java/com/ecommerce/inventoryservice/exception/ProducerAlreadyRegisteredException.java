package com.ecommerce.inventoryservice.exception;

public class ProducerAlreadyRegisteredException extends RuntimeException {

    public ProducerAlreadyRegisteredException(String message) {
        super(message);
    }
}
