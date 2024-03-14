package com.ecommerce.inventoryservice.service;

import com.ecommerce.inventoryservice.dto.ProducerDTO;
import com.ecommerce.inventoryservice.dto.StockRequest;
import com.ecommerce.inventoryservice.entity.Producer;
import com.ecommerce.inventoryservice.entity.Product;
import com.ecommerce.inventoryservice.entity.ProductCategory;
import com.ecommerce.inventoryservice.exception.MissingProductInStockException;
import com.ecommerce.inventoryservice.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    ProductRepository productRepository;
    private ProductService underTest = new ProductService(productRepository);



    @Test
    void isShouldCheckThatProductsAreNotInStock() {

        StockRequest stockRequest = StockRequest
                .builder()
                .skuCodes(List.of("a21dcv2c22323xS","sa2e352sdf3451s"))
                .build();

      Product missingProduct = Product
                .builder()
                .description("Samsung phone")
                .name("Samsung Galaxy S22")
                .price(BigDecimal.valueOf(400))
                .quantity(2)
                .productCategory(ProductCategory.COMPUTERS)
                .producer(new Producer(null,"Samsung",null))
                .skuCode("sa2e352sdf3451s")
                .build();

        when(productRepository.findProductsBySkuCodesNotInStock(stockRequest.skuCodes()))
                .thenReturn(Optional.of(List.of(missingProduct)));

        underTest = new ProductService(productRepository);

        assertThatThrownBy(() -> underTest.isInStock(stockRequest))
                .isInstanceOf(MissingProductInStockException.class)
                .hasMessageContaining("The products with the followings sku codes are not in stock: " +  List.of(missingProduct.getSkuCode()));
        verify(productRepository).findProductsBySkuCodesNotInStock(stockRequest.skuCodes());
    }

}