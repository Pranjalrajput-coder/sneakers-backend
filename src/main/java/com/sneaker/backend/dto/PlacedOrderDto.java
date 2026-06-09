package com.sneaker.backend.dto;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlacedOrderDto {

    private String deliveryDate;
    private String orderStatus;
    private String paymentStatus;
    private String paymentMode;
    private String address;
    private Double totalAmount;
    private List<OrderItemsDto> orderItems;

}
