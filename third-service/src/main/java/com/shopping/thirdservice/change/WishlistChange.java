package com.shopping.thirdservice.change;

import com.shopping.thirdservice.dto.WishlistDto;
import com.shopping.thirdservice.entity.Wishlist;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class WishlistChange {
    public Wishlist create(WishlistDto dto) {
        return Wishlist.builder()
                .customerId(dto.getCustomerId())
                .productId(dto.getProductId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public Wishlist update(Wishlist wishlist, WishlistDto dto) {
        wishlist.setCustomerId(dto.getCustomerId());
        wishlist.setProductId(dto.getProductId());
        wishlist.setUpdatedAt(LocalDateTime.now());
        return wishlist;
    }
}
