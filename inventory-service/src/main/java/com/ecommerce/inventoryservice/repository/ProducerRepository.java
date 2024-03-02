package com.ecommerce.inventoryservice.repository;


import com.ecommerce.inventoryservice.entity.Producer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProducerRepository
        extends JpaRepository<Producer,Integer> {
}
