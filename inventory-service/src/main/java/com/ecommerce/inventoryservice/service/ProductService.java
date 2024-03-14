package com.ecommerce.inventoryservice.service;

import com.ecommerce.inventoryservice.dto.ProductDTO;
import com.ecommerce.inventoryservice.dto.StockRequest;
import com.ecommerce.inventoryservice.entity.Product;
import com.ecommerce.inventoryservice.exception.MissingProductInStockException;
import com.ecommerce.inventoryservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(this::mapProductToDTO)
                .toList();
    }

    public void isInStock(@NonNull StockRequest stockRequest) {
        Optional<List<Product>> missingProducts = productRepository.findProductsBySkuCodesNotInStock(stockRequest.skuCodes());
        if (missingProducts.isPresent() && !missingProducts.get().isEmpty()) {
            throw new MissingProductInStockException("The products with the followings sku codes are not in stock: " + missingProducts.get().stream().map(Product::getSkuCode).toList());
        }
    }

    public ProductDTO mapProductToDTO(Product product) {
        return ProductDTO
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

}
