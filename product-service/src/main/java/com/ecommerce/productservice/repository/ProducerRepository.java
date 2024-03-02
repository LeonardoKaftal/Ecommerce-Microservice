package com.ecommerce.productservice.repository;

import com.ecommerce.productservice.entity.Producer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface ProducerRepository
        extends JpaRepository<Producer,Integer> {
}
