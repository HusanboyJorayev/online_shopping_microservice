package com.shopping.secondservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.shopping.secondservice.client.dto.OrderDto;
import jakarta.persistence.Entity;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaymentDto {
    private Integer id;
    private LocalDateTime date;
    private String method;
    private Integer amount;
    private Integer customerId;

    private List<OrderDto>orders;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
