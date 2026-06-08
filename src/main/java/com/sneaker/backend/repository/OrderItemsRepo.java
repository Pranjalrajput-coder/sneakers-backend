package com.sneaker.backend.repository;

import com.sneaker.backend.entities.OrderItemsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemsRepo extends JpaRepository<OrderItemsEntity, Long> {
}
