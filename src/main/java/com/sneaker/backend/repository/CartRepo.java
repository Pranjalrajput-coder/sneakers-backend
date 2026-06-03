package com.sneaker.backend.repository;

import com.sneaker.backend.entities.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CartRepo extends JpaRepository<CartEntity, Long>{

    CartEntity findByProductIdAndUserId(Long productId, Long userId);
    CartEntity findByProductIdAndSizeAndUserId(Long productId, Integer size, Long userId);
    List<CartEntity> findAllByProductIdAndUserId(Long productId, Long userId);
    List<CartEntity> findByUserId(Long userId);

    @Query("""
            SELECT c
            FROM CartEntity c
            WHERE c.userId = :userId
            """)
    List<CartEntity> getRestProducts(Long userId);
}
