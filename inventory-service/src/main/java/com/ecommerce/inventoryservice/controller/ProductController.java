package com.ecommerce.inventoryservice.controller;

import com.ecommerce.inventoryservice.dto.ProductDTO;
import com.ecommerce.inventoryservice.dto.StockRequest;
import com.ecommerce.inventoryservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/inventory/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<ProductDTO> getAllProducts() {
        return productService.getAllProducts();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String isInStock(@RequestBody @NonNull StockRequest stockRequest) {
        productService.isInStock(stockRequest);
        return "All the products are in stocks";
    }

}
