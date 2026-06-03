package com.sneaker.backend.dto;

//import com.sneaker.backend.entities.ProductSize;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponseDto {
    private Long id;
    private String name;
    private String brand;
    private Double price;
    private String description;
    private String imageUrl;
    private String category;
    private List<String> sizes;
    private Integer stock;
}
