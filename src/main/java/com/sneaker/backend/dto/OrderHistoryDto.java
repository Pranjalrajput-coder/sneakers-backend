package com.sneaker.backend.dto;

import com.sneaker.backend.entities.role.OrderStatus;
import com.sneaker.backend.entities.role.PaymentMode;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderHistoryDto {

    private Double totalAmount;
    private String address;
    private String orderDate;
    private List<OrderItemsDto> orderItems;
    private OrderStatus orderStatus;
    private PaymentMode paymentMode;
    private String deliveryDate;
}
