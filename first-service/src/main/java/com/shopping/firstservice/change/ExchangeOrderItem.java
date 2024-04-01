package com.shopping.firstservice.change;

import com.shopping.firstservice.dto.OrderItemDto;
import com.shopping.firstservice.entity.OrderItem;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ExchangeOrderItem {
    public OrderItem create(OrderItemDto dto) {
        return OrderItem.builder()
                .orderId(dto.getOrderId())
                .price(dto.getPrice())
                .productId(dto.getProductId())
                .quantity(dto.getQuantity())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public OrderItem update(OrderItem orderItem, OrderItemDto dto) {
        orderItem.setOrderId(dto.getOrderId());
        orderItem.setPrice(dto.getPrice());
        orderItem.setProductId(dto.getProductId());
        orderItem.setQuantity(dto.getQuantity());
        orderItem.setUpdatedAt(LocalDateTime.now());
        return orderItem;
    }
}
