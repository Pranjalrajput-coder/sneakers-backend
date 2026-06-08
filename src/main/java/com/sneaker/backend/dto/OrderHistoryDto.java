package com.sneaker.backend.dto;

import com.sneaker.backend.entities.CartEntity;
import com.sneaker.backend.entities.OrderItemsEntity;
import com.sneaker.backend.entities.OrdersEntity;
import com.sneaker.backend.entities.UserEntity;
import com.sneaker.backend.entities.role.OrderStatus;
import com.sneaker.backend.entities.role.PaymentMode;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderHistoryDto {

    private Double totalAmount;
    private String address;
    private LocalDateTime createdAt;
    private List<OrderItemsEntity> orderItems;
    private OrderStatus orderStatus;
    private PaymentMode paymentMode;
    private String deliveryDate;
}
