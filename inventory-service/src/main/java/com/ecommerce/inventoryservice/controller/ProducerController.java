package com.ecommerce.inventoryservice.controller;

import com.ecommerce.inventoryservice.dto.ProducerDTO;
import com.ecommerce.inventoryservice.service.ProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/inventory/producers")
public class ProducerController {

    private final ProducerService producerService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ProducerDTO registerProducer(@RequestBody @NonNull ProducerDTO producerDTO) {
        return producerService.saveProducer(producerDTO);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<ProducerDTO> getAllProducers() {
        return producerService.getAllProducers();
    }

    @GetMapping("/{producerName}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ProducerDTO getProducerByName(@PathVariable String producerName) {
        return producerService.findProducerByName(producerName).producerDTO();
    }

}
