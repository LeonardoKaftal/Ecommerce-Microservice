package com.ecommerce.inventoryservice.exception;

public class MissingProductsInStockException extends RuntimeException {
    public MissingProductsInStockException(String message) {
        super(message);
    }
}
