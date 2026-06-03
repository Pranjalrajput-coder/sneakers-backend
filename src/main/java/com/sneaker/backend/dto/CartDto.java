package com.sneaker.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDto {
    private Long userId;
    private Long productId;

    // Just Have These for Response
    private Integer size;
    private Integer quantity;
    private Double productPrice;
    private String productName;
    private String productImage;
    private String productBrand;
}
