package com.ecommerce.inventoryservice.service;

import com.ecommerce.inventoryservice.dto.ProductRequest;
import com.ecommerce.inventoryservice.dto.ProductResponse;
import com.ecommerce.inventoryservice.dto.StockRequest;
import com.ecommerce.inventoryservice.entity.Product;
import com.ecommerce.inventoryservice.exception.MissingProductsInStockException;
import com.ecommerce.inventoryservice.exception.ProductAlreadyRegisteredException;
import com.ecommerce.inventoryservice.exception.ProductNotFoundException;
import com.ecommerce.inventoryservice.repository.ProductRepository;
import com.ecommerce.inventoryservice.util.ProducerTuple;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;
    private final ProducerService producerService;

    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(this::mapProductToDTO)
                .toList();
    }

    public ProductResponse saveProduct(@NonNull ProductRequest productRequest) {
        if (productRepository.findProductByName(productRequest.name()).isPresent()) {
            throw new ProductAlreadyRegisteredException("Product " + productRequest.name() + " is already registered");
        }

        ProducerTuple producerTuple = producerService.findProducerByName(productRequest.producerName());

        Product product = Product
                .builder()
                .productCategory(productRequest.productCategory())
                .description(productRequest.description())
                .name(productRequest.name())
                .price(productRequest.price())
                .skuCode(UUID.randomUUID().toString())
                .producer(producerTuple.producer())
                .quantity(productRequest.quantity())
                .build();

        log.info("Saving new product with SkuCode {} in the database", product.getSkuCode());
        productRepository.save(product);
        return mapProductToDTO(product);
    }


    public void isInStock(@NonNull StockRequest stockRequest) {
        List<String> missingProductsSkuCodes = new ArrayList<>();
        for (String skuCode: stockRequest.skuCodes()) {
            Optional<Product> product = productRepository.findProductBySkuCode(skuCode);
            if (product.isPresent()) {
                if (product.get().getQuantity() <= 0) {
                    missingProductsSkuCodes.add(skuCode);
                }
            }
            else {
                missingProductsSkuCodes.add(skuCode);
            }
        }
        if (!missingProductsSkuCodes.isEmpty()) {
            throw new MissingProductsInStockException("The following products with skuCodes: " + missingProductsSkuCodes + " are not in stock");
        }
    }

    public ProductResponse mapProductToDTO(Product product) {
        return ProductResponse
                .builder()
                .productCategory(product.getProductCategory())
                .price(product.getPrice())
                .description(product.getDescription())
                .producerName(product.getProducer().getName())
                .name(product.getName())
                .quantity(product.getQuantity())
                .skuCode(product.getSkuCode())
                .build();
    }

    public ProductResponse findProductBySkuCode(String skuCode) {
        Product product = productRepository.findProductBySkuCode(skuCode)
                .orElseThrow(()-> new ProductNotFoundException(String.format("Product with SkuCode %s has not been found", skuCode)));
        return mapProductToDTO(product);
    }
}
