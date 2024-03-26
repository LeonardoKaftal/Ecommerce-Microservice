package com.ecommerce.inventoryservice.service;

import com.ecommerce.inventoryservice.dto.ProducerDTO;
import com.ecommerce.inventoryservice.dto.ProductRequest;
import com.ecommerce.inventoryservice.dto.ProductResponse;
import com.ecommerce.inventoryservice.entity.Producer;
import com.ecommerce.inventoryservice.entity.Product;
import com.ecommerce.inventoryservice.exception.ProducerAlreadyRegisteredException;
import com.ecommerce.inventoryservice.exception.ProducerNotFoundException;
import com.ecommerce.inventoryservice.repository.ProducerRepository;
import com.ecommerce.inventoryservice.util.ProducerTuple;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProducerService {
    private final ProducerRepository producerRepository;

    public ProducerTuple findProducerByName(@NonNull String name) {
        Producer producer = producerRepository.findProducerByName(name)
                .orElseThrow(() -> new ProducerNotFoundException("Producer " + name + " has not been found, NOTE THAT for registering a product in the inventory you must register the producer eg: Samsung, Apple ecc... first."));
        return new ProducerTuple(mapProducerToProducerDTO(producer),producer);
    }

    public ProducerDTO mapProducerToProducerDTO(@NonNull Producer producer) {
        return ProducerDTO
                .builder()
                .name(producer.getName())
                .products(producer.getProducts().stream().map(this::mapProductToDTO).toList())
                .build();
    }

    public ProducerDTO saveProducer(@NonNull ProducerDTO producerRegisterRequest) {
        if (producerRepository.findProducerByName(producerRegisterRequest.name()).isPresent()) {
            throw new ProducerAlreadyRegisteredException("Producer with name " + producerRegisterRequest.name() + " is already registered");
        }
        Producer producer = Producer
                .builder()
                .name(producerRegisterRequest.name())
                .products(new ArrayList<>())
                .build();
        log.info("Saving new producer with name {}", producer.getName());
        producerRepository.save(producer);
        return producerRegisterRequest;
    }

    private ProductRequest mapProductToDTO(Product product) {
        return ProductRequest
                .builder()
                .productCategory(product.getProductCategory())
                .price(product.getPrice())
                .description(product.getDescription())
                .producerName(product.getProducer().getName())
                .name(product.getName())
                .quantity(product.getQuantity())
                .build();
    }

    public List<ProducerDTO> getAllProducers() {
        return producerRepository.findAll().stream().map(this::mapProducerToProducerDTO).toList();
    }
}
