package com.shopping.secondservice.change;

import com.shopping.secondservice.dto.PaymentDto;
import com.shopping.secondservice.entity.Payment;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class PaymentChange {

    public Payment create(PaymentDto dto) {
        return Payment.builder()
                .amount(dto.getAmount())
                .date(LocalDateTime.now())
                .customerId(dto.getCustomerId())
                .method(dto.getMethod())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public Payment update(PaymentDto dto, Payment payment) {
        payment.setAmount(dto.getAmount());
        payment.setDate(LocalDateTime.now());
        payment.setMethod(dto.getMethod());
        payment.setCustomerId(dto.getCustomerId());
        payment.setUpdatedAt(LocalDateTime.now());
        return payment;
    }
}
