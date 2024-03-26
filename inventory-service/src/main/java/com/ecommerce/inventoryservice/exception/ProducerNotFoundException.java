package com.ecommerce.inventoryservice.exception;

public class ProducerNotFoundException extends RuntimeException {
    public ProducerNotFoundException(String message) {
        super(message);
    }
}
