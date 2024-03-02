package com.ecommerce.productservice.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record ProducerDTO(String name, List<ProductDTO> products) {
}
