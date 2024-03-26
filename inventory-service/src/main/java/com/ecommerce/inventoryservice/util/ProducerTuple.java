package com.ecommerce.inventoryservice.util;

import com.ecommerce.inventoryservice.dto.ProducerDTO;
import com.ecommerce.inventoryservice.entity.Producer;

public record ProducerTuple(ProducerDTO producerDTO, Producer producer) {
}
