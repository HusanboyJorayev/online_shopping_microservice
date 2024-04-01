package com.shopping.secondservice.change;

import com.shopping.secondservice.dto.CartDto;
import com.shopping.secondservice.entity.Cart;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CartChange {

    public Cart create(CartDto dto) {
        return Cart.builder()
                .customerId(dto.getCustomerId())
                .productId(dto.getProductId())
                .quantity(dto.getQuantity())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public Cart update(CartDto dto, Cart cart) {
        cart.setQuantity(dto.getQuantity());
        cart.setCustomerId(dto.getCustomerId());
        cart.setProductId(dto.getProductId());
        cart.setUpdatedAt(LocalDateTime.now());
        return cart;
    }
}
