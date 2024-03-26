package com.ecommerce.inventoryservice.dto;


import lombok.Builder;

import java.util.List;

@Builder
public record ProducerDTO(String name, List<ProductRequest> products) {
}
