package com.ecommerce.inventoryservice.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record StockRequest(List<String> skuCodes) {
}
