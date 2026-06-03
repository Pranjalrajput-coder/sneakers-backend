package com.sneaker.backend.repository;

import com.sneaker.backend.entities.WishlistEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WishListRepo extends JpaRepository<WishlistEntity, Long> {

    Boolean findByProductIdAndUserId(Long productId, Long userId);
    WishlistEntity findByUserIdAndProductId(Long userId, Long productId);

    @Query("""
            SELECT w
            FROM WishlistEntity w
            WHERE w.userId = :userId
            """)
    List<WishlistEntity> getRestItemInWishList(Long userId);

    List<WishlistEntity> findByUserId(Long userId);
}
