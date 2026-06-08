package com.sneaker.backend.entities;

import com.sneaker.backend.entities.role.PaymentMode;
import com.sneaker.backend.entities.role.PaymentStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@Table(name = "payment")
public class PaymentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double amount;

    @Enumerated(EnumType.STRING)
    private PaymentMode paymentMode;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    @OneToOne
    @JoinColumn(name = "order_id")
    private OrdersEntity ordersEntity;

    private String mockTxnId;
}
