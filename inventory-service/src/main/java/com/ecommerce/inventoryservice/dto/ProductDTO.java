package com.ecommerce.inventoryservice.dto;

import com.ecommerce.inventoryservice.entity.ProductCategory;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record ProductDTO(String name, BigDecimal price, String description, Integer quantity, ProductCategory productCategory, String skuCode, String producerName) {
}
