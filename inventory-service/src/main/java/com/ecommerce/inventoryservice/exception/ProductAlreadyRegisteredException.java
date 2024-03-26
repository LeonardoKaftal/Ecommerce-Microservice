package com.ecommerce.inventoryservice.exception;

public class ProductAlreadyRegisteredException extends RuntimeException {
    public ProductAlreadyRegisteredException(String message) {
        super(message);
    }
}
