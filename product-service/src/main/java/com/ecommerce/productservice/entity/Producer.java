package com.ecommerce.productservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Producer {
    @Id
    @SequenceGenerator(
            name = "producer_sequence",
            sequenceName = "producer_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "producer_sequence"
    )
    private Integer producerId;
    private String name;
    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL, mappedBy = "producer")
    @JsonIgnoreProperties("producer")
    private List<Product> products;
}
