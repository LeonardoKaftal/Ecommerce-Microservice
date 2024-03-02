package com.ecommerce.inventoryservice.repository;


import com.ecommerce.inventoryservice.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository
        extends JpaRepository<Product,Integer> {
}
