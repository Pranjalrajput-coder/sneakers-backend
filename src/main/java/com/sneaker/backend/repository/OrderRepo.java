package com.sneaker.backend.repository;

import com.sneaker.backend.entities.OrdersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;

public interface OrderRepo extends JpaRepository<OrdersEntity, Long> {
    List<OrdersEntity> findByUserId(Long userId);
}
