package com.ecommerce.inventoryservice.repository;


import com.ecommerce.inventoryservice.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository
        extends JpaRepository<Product,Integer> {

    @Query("SELECT p FROM Product p WHERE p.skuCode IN :skuCodes AND (p.quantity IS NULL OR p.quantity <= 0) OR p.skuCode NOT IN (SELECT s.skuCode FROM Product s)")
    Optional<List<Product>> findProductsBySkuCodesNotInStock(List<String> skuCodes);
}
