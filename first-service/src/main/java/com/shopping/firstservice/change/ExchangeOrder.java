package com.shopping.firstservice.change;

import com.shopping.firstservice.dto.CustomerDto;
import com.shopping.firstservice.dto.OrderDto;
import com.shopping.firstservice.entity.Customer;
import com.shopping.firstservice.entity.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ExchangeOrder {
    public Order create(OrderDto dto) {
        return Order.builder()
                .customerId(dto.getCustomerId())
                .dateTime(LocalDateTime.now())
                .paymentId(dto.getPaymentId())
                .shipmentId(dto.getShipmentId())
                .totalPrice(dto.getTotalPrice())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public Order update(Order order, OrderDto dto) {
        order.setCustomerId(dto.getCustomerId());
        order.setPaymentId(dto.getPaymentId());
        order.setShipmentId(dto.getShipmentId());
        order.setTotalPrice(dto.getTotalPrice());
        order.setUpdatedAt(LocalDateTime.now());
        return order;
    }
}
