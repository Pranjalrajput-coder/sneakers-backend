package com.sneaker.backend.dto;

import com.sneaker.backend.entities.role.PaymentMode;
import com.sneaker.backend.entities.role.PaymentStatus;
import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDto {

    private PaymentStatus status;
    private PaymentMode paymentMode;
    private Double amount;
    private LocalDateTime createdAt;

}
