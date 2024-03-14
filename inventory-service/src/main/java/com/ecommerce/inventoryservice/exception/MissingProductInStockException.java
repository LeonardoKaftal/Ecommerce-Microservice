package com.ecommerce.inventoryservice.exception;

public class MissingProductInStockException extends RuntimeException {
    public MissingProductInStockException(String message) {
        super(message);
    }
}
