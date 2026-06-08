package com.sneaker.backend.dto;

import com.sneaker.backend.entities.CartEntity;
import com.sneaker.backend.entities.OrderItemsEntity;
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
    private List<OrderItemsEntity> orderItems;

}
