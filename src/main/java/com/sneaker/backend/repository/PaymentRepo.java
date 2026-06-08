package com.sneaker.backend.repository;

import com.sneaker.backend.entities.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepo extends JpaRepository<PaymentEntity, Long> {
}
