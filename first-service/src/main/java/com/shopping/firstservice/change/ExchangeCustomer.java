package com.shopping.firstservice.change;

import com.shopping.firstservice.dto.CustomerDto;
import com.shopping.firstservice.entity.Customer;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ExchangeCustomer {
    public Customer create(CustomerDto dto) {
        return Customer.builder()
                .address(dto.getAddress())
                .email(dto.getEmail())
                .lastname(dto.getLastname())
                .firstname(dto.getFirstname())
                .password(dto.getPassword())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public Customer update(Customer customer, CustomerDto dto) {
        customer.setAddress(dto.getAddress());
        customer.setEmail(dto.getEmail());
        customer.setLastname(dto.getLastname());
        customer.setFirstname(dto.getFirstname());
        customer.setPassword(dto.getPassword());
        customer.setUpdatedAt(LocalDateTime.now());
        return customer;
    }

}
