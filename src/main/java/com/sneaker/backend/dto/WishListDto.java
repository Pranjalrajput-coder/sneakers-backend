package com.sneaker.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WishListDto {

    private Long userId;
    private Long productId;

    // Just Have These for Response
    private Double productPrice;
    private String productName;
    private String productImage;
    private String productBrand;

}
