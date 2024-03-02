package com.ecommerce.productservice.dto;

import com.ecommerce.productservice.entity.Producer;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record ProductDTO(String name, BigDecimal price, ProducerDTO producer) {
}
