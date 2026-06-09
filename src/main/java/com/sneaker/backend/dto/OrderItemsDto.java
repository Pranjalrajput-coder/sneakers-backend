package com.sneaker.backend.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderItemsDto {
    private String productName;
    private String productImage;
    private Double productPrice;
    private Integer productQty;
    private String productSize;
    private String productDescription;
    private String productBrand;
}
