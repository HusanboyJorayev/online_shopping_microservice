package com.shopping.firstservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.shopping.firstservice.clent.dto.CartDto;
import com.shopping.firstservice.clent.dto.PaymentDto;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerDto {
    private Integer id;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private String address;
    private List<CartDto>carts;
    private List<PaymentDto>payments;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
