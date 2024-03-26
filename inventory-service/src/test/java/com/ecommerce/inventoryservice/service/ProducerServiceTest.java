package com.ecommerce.inventoryservice.service;

import com.ecommerce.inventoryservice.dto.ProducerDTO;
import com.ecommerce.inventoryservice.entity.Producer;
import com.ecommerce.inventoryservice.entity.Product;
import com.ecommerce.inventoryservice.entity.ProductCategory;
import com.ecommerce.inventoryservice.exception.ProducerAlreadyRegisteredException;
import com.ecommerce.inventoryservice.exception.ProducerNotFoundException;
import com.ecommerce.inventoryservice.repository.ProducerRepository;
import com.ecommerce.inventoryservice.util.ProducerTuple;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ProducerServiceTest {

    @Mock
    ProducerRepository producerRepository;
    private ProducerService underTest;


    @Test
    void shouldSearchProducerByName() {
        Producer dbResult = Producer
                .builder()
                .name("Samsung")
                .products(List.of(
                        new Product(null,"a12c","S6", BigDecimal.TWO,"Abc",2, ProductCategory.PHONES,new Producer(null,"Samsung",null)),
                        new Product(null,"s414f","S9",BigDecimal.TWO,"ao",2,ProductCategory.PHONES,new Producer(null,"Samsung",null))))
                .build();

        Mockito.when(producerRepository.findProducerByName(dbResult.getName()))
                .thenReturn(Optional.of(dbResult));
        underTest = new ProducerService(producerRepository);
        ProducerDTO dbResultDto = underTest.mapProducerToProducerDTO(dbResult);

        ProducerTuple expectedResponse = new ProducerTuple(dbResultDto,dbResult);

        Mockito.when(producerRepository.findProducerByName(dbResult.getName()))
                .thenReturn(Optional.of(dbResult));
        underTest = new ProducerService(producerRepository);

        assertThat(underTest.findProducerByName(dbResult.getName())).isEqualTo(expectedResponse);
        verify(producerRepository).findProducerByName(dbResult.getName());
    }

    @Test
    void shouldThrowAnExceptionSearchingForNonExistingProducer() {
        String producerName = "Samsung";

        Mockito.when(producerRepository.findProducerByName(producerName))
                .thenReturn(Optional.empty());
        underTest = new ProducerService(producerRepository);

        assertThatThrownBy(()-> underTest.findProducerByName(producerName))
                .isInstanceOf(ProducerNotFoundException.class)
                .hasMessageContaining("Producer " + producerName + " has not been found, NOTE THAT for registering a product in the inventory you must register the producer eg: Samsung, Apple ecc... first.");

        verify(producerRepository).findProducerByName(producerName);
    }

    @Test
    void shouldSaveProducer() {
        Producer producer = Producer
                .builder()
                .name("Apple")
                .products(List.of(new Product(null, UUID.randomUUID().toString(),"Iphone 6",BigDecimal.TEN,"Phone",2,ProductCategory.PHONES,null)))
                .build();
        producer.getProducts().forEach(product -> product.setProducer(producer));
        Mockito.when(producerRepository.findProducerByName(producer.getName())).thenReturn(Optional.empty());
        underTest = new ProducerService(producerRepository);
        ProducerDTO request = underTest.mapProducerToProducerDTO(producer);
        assertThat(underTest.saveProducer(request)).isEqualTo(request);
    }

    @Test
    void shouldThrowAnExceptionRegisteringAnAlreadyRegisteredProducer() {
        Producer producer = Producer
                .builder()
                .name("Apple")
                .products(List.of(new Product(null, UUID.randomUUID().toString(),"Iphone 6",BigDecimal.TEN,"Phone",2,ProductCategory.PHONES,null)))
                .build();
        producer.getProducts().forEach(product -> product.setProducer(producer));
        Mockito.when(producerRepository.findProducerByName(producer.getName())).thenReturn(Optional.of(producer));
        underTest = new ProducerService(producerRepository);
        ProducerDTO request = underTest.mapProducerToProducerDTO(producer);

        assertThatThrownBy(()-> underTest.saveProducer(request))
                .isInstanceOf(ProducerAlreadyRegisteredException.class)
                .hasMessageContaining("Producer with name " + producer.getName() + " is already registered");
    }


}