package com.ecommerce.inventoryservice.service;

import com.ecommerce.inventoryservice.dto.ProducerDTO;
import com.ecommerce.inventoryservice.dto.ProductRequest;
import com.ecommerce.inventoryservice.dto.ProductResponse;
import com.ecommerce.inventoryservice.dto.StockRequest;
import com.ecommerce.inventoryservice.entity.Producer;
import com.ecommerce.inventoryservice.entity.Product;
import com.ecommerce.inventoryservice.entity.ProductCategory;
import com.ecommerce.inventoryservice.exception.MissingProductsInStockException;
import com.ecommerce.inventoryservice.exception.ProducerNotFoundException;
import com.ecommerce.inventoryservice.exception.ProductAlreadyRegisteredException;
import com.ecommerce.inventoryservice.exception.ProductNotFoundException;
import com.ecommerce.inventoryservice.repository.ProductRepository;
import com.ecommerce.inventoryservice.util.ProducerTuple;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    ProductRepository productRepository;
    @Mock
    ProducerService producerService;
    private ProductService underTest = new ProductService(productRepository,producerService);


    @Test
    void itShouldRegisterProduct() {

        Product productToSave = Product
                .builder()
                .productCategory(ProductCategory.PHONES)
                .description("phone")
                .price(BigDecimal.valueOf(300))
                .build();
        ProductRequest productRequest = ProductRequest
                .builder()
                .productCategory(ProductCategory.PHONES)
                .description(productToSave.getDescription())
                .name(productToSave.getName())
                .quantity(productToSave.getQuantity())
                .price(productToSave.getPrice())
                .producerName("Samsung")
                .build();
        Producer producer = Producer
                .builder()
                .name("Samsung")
                .products(List.of(productToSave))
                .build();
        productToSave.setProducer(producer);
        ProducerDTO producerDTO = ProducerDTO
                .builder()
                .name(producer.getName())
                // map product to dto for every product of a producer
                .products(producer.getProducts()
                        .stream()
                        .map(product -> ProductRequest.builder()
                                        .productCategory(product.getProductCategory())
                                        .producerName(product.getProducer().getName())
                                        .quantity(product.getQuantity())
                                        .description(product.getDescription())
                                        .price(product.getPrice())
                                        .build())
                        .toList())
                .build();

        ProductResponse expectedResponse = underTest.mapProductToDTO(productToSave);
        ProducerTuple producerTuple = new ProducerTuple(producerDTO,producer);


        when(productRepository.findProductByName(productToSave.getName()))
                .thenReturn(Optional.empty());
        when(producerService.findProducerByName(productToSave.getProducer().getName()))
                .thenReturn(producerTuple);

        underTest = new ProductService(productRepository, producerService);

        ProductResponse actualServiceResponse = underTest.saveProduct(productRequest);
        expectedResponse.setSkuCode(actualServiceResponse.getSkuCode());
        assertThat(actualServiceResponse).isEqualTo(expectedResponse);

        verify(productRepository).findProductByName(productToSave.getName());
    }

    @Test
    void itShouldThrowAnExceptionRegisteringProductAlreadyRegistered() {
        Product productToSave = Product
                .builder()
                .productCategory(ProductCategory.PHONES)
                .description("phone")
                .skuCode(UUID.randomUUID().toString())
                .price(BigDecimal.valueOf(300))
                .build();
        ProductRequest productRequest = ProductRequest
                .builder()
                .productCategory(ProductCategory.PHONES)
                .description(productToSave.getDescription())
                .name(productToSave.getName())
                .quantity(productToSave.getQuantity())
                .price(productToSave.getPrice())
                .build();

        when(productRepository.findProductByName(productToSave.getName()))
                .thenReturn(Optional.of(productToSave));

        underTest = new ProductService(productRepository, producerService);
        assertThatThrownBy(()-> underTest.saveProduct(productRequest))
                .isInstanceOf(ProductAlreadyRegisteredException.class)
                .hasMessageContaining("Product " + productRequest.name() + " is already registered");
    }

    @Test
    void itShouldThrowAnExceptionRegisteringAProductWithNonExistingProducer() {
        Product request = Product
                .builder()
                .productCategory(ProductCategory.PHONES)
                .description("phone")
                .skuCode(UUID.randomUUID().toString())
                .price(BigDecimal.valueOf(300))
                .build();
        Producer producer = Producer
                .builder()
                .name("Samsung")
                .products(List.of(request))
                .build();
        request.setProducer(producer);
        ProductResponse productResponse = underTest.mapProductToDTO(request);
        ProductRequest productRequest = ProductRequest
                .builder()
                .price(request.getPrice())
                .productCategory(request.getProductCategory())
                .quantity(request.getQuantity())
                .description(request.getDescription())
                .name(request.getName())
                .producerName(productResponse.getProducerName())
                .build();

        when(producerService.findProducerByName(request.getProducer().getName()))
                .thenThrow(new ProducerNotFoundException("Producer " + producer.getName() + " has not been found, NOTE THAT for registering a product in the inventory you must register the producer eg: Samsung, Apple ecc... first."));

        underTest = new ProductService(productRepository, producerService);
        assertThatThrownBy(()-> underTest.saveProduct(productRequest))
                .isInstanceOf(ProducerNotFoundException.class)
                .hasMessageContaining("Producer " + producer.getName() + " has not been found, NOTE THAT for registering a product in the inventory you must register the producer eg: Samsung, Apple ecc... first.");
        verify(productRepository).findProductByName(request.getName());
    }

    @Test
    void itShouldCheckThatProductsAreNotInStock() {

        StockRequest stockRequest = StockRequest
                .builder()
                .skuCodes(List.of("a21dcv2c22323xS", "sa2e352sdf3451s", "Sww23sda234435456as"))
                .build();

        Product registeredProductWithQuantity0 = Product
                .builder()
                .quantity(0)
                .productCategory(ProductCategory.PHONES)
                .description("asdws")
                .producer(null)
                .price(BigDecimal.TEN)
                .name("Motorola 10")
                .skuCode(stockRequest.skuCodes().getFirst())
                .build();
        Product registeredProduct = Product
                .builder()
                .skuCode(stockRequest.skuCodes().get(1))
                .productCategory(ProductCategory.PHONES)
                .description("assASDAD")
                .quantity(2)
                .name("Samsung S10")
                .price(BigDecimal.TEN)
                .build();

        when(productRepository.findProductBySkuCode(stockRequest.skuCodes().getFirst()))
                .thenReturn(Optional.of(registeredProductWithQuantity0));
        when(productRepository.findProductBySkuCode(stockRequest.skuCodes().get(1)))
                .thenReturn(Optional.empty());
        when(productRepository.findProductBySkuCode(stockRequest.skuCodes().get(2)))
                .thenReturn(Optional.of(registeredProduct));

        underTest = new ProductService(productRepository,producerService);
        assertThatThrownBy(()-> underTest.isInStock(stockRequest))
                .isInstanceOf(MissingProductsInStockException.class)
                .hasMessageContaining("The following products with skuCodes: " + List.of(stockRequest.skuCodes().getFirst(),stockRequest.skuCodes().get(1)) + " are not in stock");
    }

    @Test
    void itShouldCheckThatProductAreAllInStock() {
        StockRequest stockRequest = StockRequest
                .builder()
                .skuCodes(List.of("a21dcv2c22323xS", "Sww23sda234435456as"))
                .build();

        Product registeredProduct = Product
                .builder()
                .quantity(2)
                .productCategory(ProductCategory.PHONES)
                .description("asdws")
                .producer(null)
                .price(BigDecimal.TEN)
                .name("Motorola 10")
                .skuCode(stockRequest.skuCodes().getFirst())
                .build();
        Product anotherRegisteredProduct = Product
                .builder()
                .skuCode(stockRequest.skuCodes().get(1))
                .productCategory(ProductCategory.PHONES)
                .description("assASDAD")
                .quantity(2)
                .name("Samsung S10")
                .price(BigDecimal.TEN)
                .build();

        when(productRepository.findProductBySkuCode(stockRequest.skuCodes().getFirst()))
                .thenReturn(Optional.of(registeredProduct));
        when(productRepository.findProductBySkuCode(stockRequest.skuCodes().get(1)))
                .thenReturn(Optional.of(anotherRegisteredProduct));

        underTest = new ProductService(productRepository,producerService);
        assertThatCode(()-> underTest.isInStock(stockRequest)).doesNotThrowAnyException();
        verify(productRepository).findProductBySkuCode(stockRequest.skuCodes().getFirst());
        verify(productRepository).findProductBySkuCode(stockRequest.skuCodes().get(1));
    }

    @Test
    void itShouldSearchProductBySkuCode() {
        Product product = Product
                .builder()
                .price(BigDecimal.TEN)
                .name("Samsung S10")
                .skuCode(UUID.randomUUID().toString())
                .quantity(1)
                .description("dsdsdsd")
                .productCategory(ProductCategory.PHONES)
                .producer(new Producer(null,"Samsung",new ArrayList<>()))
                .build();
        when(productRepository.findProductBySkuCode(product.getSkuCode()))
                .thenReturn(Optional.of(product));
        underTest = new ProductService(productRepository, producerService);
        ProductResponse expectedResponse = underTest.mapProductToDTO(product);
        assertThat(underTest.findProductBySkuCode(product.getSkuCode())).isEqualTo(expectedResponse);
        verify(productRepository).findProductBySkuCode(product.getSkuCode());
    }

    @Test
    void itShouldThrowAnExceptionSearchingForANonRegisteredProduct() {
        String skuCode = UUID.randomUUID().toString();
        when(productRepository.findProductBySkuCode(skuCode))
                .thenReturn(Optional.empty());
        underTest = new ProductService(productRepository,producerService);
        assertThatThrownBy(()-> underTest.findProductBySkuCode(skuCode))
                .isInstanceOf(ProductNotFoundException.class)
                .hasMessageContaining(String.format("Product with SkuCode %s has not been found", skuCode));
    }

}