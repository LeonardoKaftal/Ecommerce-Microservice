package com.ecommerce.inventoryservice.dto;

import com.ecommerce.inventoryservice.entity.ProductCategory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Objects;

@Builder
@Data
@AllArgsConstructor
public class ProductResponse {
    private String name;
    private BigDecimal price;
    private String description;
    private Integer quantity;
    private ProductCategory productCategory;
    private String skuCode;
    private String producerName;


}
