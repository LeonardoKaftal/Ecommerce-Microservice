package com.ecommerce.inventoryservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ecommerce.inventoryservice.entity.ProductCategory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Builder;

import java.io.IOException;
import java.math.BigDecimal;

@JsonDeserialize(builder = ProductRequest.ProductRequestBuilder.class)
@Builder
public record ProductRequest(
        @JsonProperty("name") String name,
        @JsonProperty("price") BigDecimal price,
        @JsonProperty("description") String description,
        @JsonProperty("quantity") Integer quantity,
        @JsonDeserialize(using = ProductCategoryDeserializer.class) @JsonProperty("productCategory") ProductCategory productCategory,
        @JsonProperty("producerName") String producerName
) {
    public static class ProductCategoryDeserializer extends JsonDeserializer<ProductCategory> {
        @Override
        public ProductCategory deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
            String categoryString = p.getValueAsString();
            return ProductCategory.valueOf(categoryString.toUpperCase());
        }
    }
}
