package com.ecommerce.inventoryservice.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MissingProductsInStockException.class)
    public ResponseEntity<ApiError> handleProductsNotInStock(MissingProductsInStockException e, HttpServletRequest request) {
        ApiError apiError = new ApiError(
                request.getRequestURI(),
                e.getMessage(),
                HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ProductAlreadyRegisteredException.class)
    public ResponseEntity<ApiError> handleProductAlreadyRegistered(ProductAlreadyRegisteredException e, HttpServletRequest request) {
        ApiError apiError = new ApiError(
                request.getRequestURI(),
                e.getMessage(),
                HttpStatus.CONFLICT.value(),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(apiError, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ProducerNotFoundException.class)
    public ResponseEntity<ApiError> handleProducerNotFound(ProducerNotFoundException e, HttpServletRequest request) {
        ApiError apiError = new ApiError(
                request.getRequestURI(),
                e.getMessage(),
                HttpStatus.NOT_FOUND.value(),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ProducerAlreadyRegisteredException.class)
    public ResponseEntity<ApiError> handleProducerAlreadyRegisteredException(ProducerAlreadyRegisteredException e, HttpServletRequest request) {
        ApiError apiError = new ApiError(
                request.getRequestURI(),
                e.getMessage(),
                HttpStatus.CONFLICT.value(),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(apiError, HttpStatus.CONFLICT);
    }

}
