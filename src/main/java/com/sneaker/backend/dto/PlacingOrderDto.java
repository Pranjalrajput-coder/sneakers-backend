package com.sneaker.backend.dto;

import com.sneaker.backend.entities.role.PaymentMode;
import lombok.Data;

@Data
public class PlacingOrderDto {

    private String address;
    private PaymentMode paymentMode;
}
