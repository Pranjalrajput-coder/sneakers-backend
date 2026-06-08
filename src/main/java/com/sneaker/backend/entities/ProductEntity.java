package com.sneaker.backend.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Map;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@Table(name = "products")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String brand;
    private Double price;
    private String description;
    private String imageUrl;
    private String category;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "product_sizes",
        joinColumns = @JoinColumn(name = "product_id"))
    @MapKeyColumn(name = "size")
    @Column(name = "stock")
    private Map<String, Integer> sizeStock;

}
