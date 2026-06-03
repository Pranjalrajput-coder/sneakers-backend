package com.sneaker.backend.dto;

//import com.sneaker.backend.entities.ProductSize;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequestDto {

    private String name;
    private String brand;
    private Double price;
    private String category;
    private List<String> sizes;
    private String imageUrl;
    private String description;
    private Integer stock;

}
