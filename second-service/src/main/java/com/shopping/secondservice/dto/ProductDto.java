package com.shopping.secondservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.shopping.secondservice.client.dto.OrderItemDto;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDto {
    private Integer id;
    private String sku;
    private String description;
    private Double price;
    private Integer stock;
    private Integer categoryId;

    private List<OrderItemDto>orderItems;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
