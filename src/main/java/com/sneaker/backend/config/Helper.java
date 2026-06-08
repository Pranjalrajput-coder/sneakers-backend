package com.sneaker.backend.config;

import com.sneaker.backend.entities.CartEntity;
import com.sneaker.backend.entities.UserEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Helper {
    // To Fetch the current user from security context

    public Long getCurrentUserId() {
        UserEntity user = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user.getId();
    }

    public UserEntity getCurrentUser() {
        return (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public Double calculateTotalAmount(List<CartEntity> orderItems){
        return orderItems.stream().mapToDouble(cart -> cart.getQuantity() * cart.getProduct().getPrice()).sum();
    }

}
