package com.ecommerce.inventoryservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
    @Id
    @SequenceGenerator(
            name = "product_sequence",
            sequenceName = "product_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "product_sequence"
    )
    private Integer productId;
    private String skuCode;
    private String name;
    private BigDecimal price;
    private String description;
    private Integer quantity;
    @Enumerated(EnumType.STRING)
    private ProductCategory productCategory;
    @ManyToOne
    private Producer producer;
}
