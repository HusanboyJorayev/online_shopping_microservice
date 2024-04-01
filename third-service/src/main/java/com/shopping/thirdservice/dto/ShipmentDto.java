package com.shopping.thirdservice.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.shopping.thirdservice.client.dto.OrderDto;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ShipmentDto {
    private Integer id;
    private LocalDateTime date;
    private String address;
    private String city;
    private String country;
    private String state;
    private String zipCode;
    private Integer customerId;

    private List<OrderDto>orders;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
