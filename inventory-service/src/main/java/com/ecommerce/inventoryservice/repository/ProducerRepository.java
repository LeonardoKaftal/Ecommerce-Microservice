package com.ecommerce.inventoryservice.repository;


import com.ecommerce.inventoryservice.entity.Producer;
import com.ecommerce.inventoryservice.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface ProducerRepository
        extends JpaRepository<Producer,Integer> {
    Optional<Producer> findProducerByName(String name);
}
