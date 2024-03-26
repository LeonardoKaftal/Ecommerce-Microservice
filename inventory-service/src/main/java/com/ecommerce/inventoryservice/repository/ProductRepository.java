package com.ecommerce.inventoryservice.repository;


import com.ecommerce.inventoryservice.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ProductRepository
        extends JpaRepository<Product,Integer> {

    Optional<Product> findProductBySkuCode(String skuCode);
    Optional<Product> findProductByName(String name);


}


