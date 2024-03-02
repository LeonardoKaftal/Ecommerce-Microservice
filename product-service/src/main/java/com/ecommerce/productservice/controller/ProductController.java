package com.ecommerce.productservice.controller;

import com.ecommerce.productservice.dto.ProductDTO;
import com.ecommerce.productservice.entity.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    @GetMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<ProductDTO> getAllProducts() {

    }
}
