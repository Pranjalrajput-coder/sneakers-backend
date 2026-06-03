package com.sneaker.backend.repository;

import com.sneaker.backend.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepo extends JpaRepository<ProductEntity, Long> {

    List<ProductEntity> findByBrand(String brand);
    List<ProductEntity> findByCategory(String category);
    List<ProductEntity> findByNameContainingIgnoreCase(String name);
    List<ProductEntity> findByPriceBetween(double min, double max);
}
