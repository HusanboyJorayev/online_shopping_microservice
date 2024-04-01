package com.shopping.secondservice.client;

import com.shopping.secondservice.client.dto.OrderItemDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient("FIRST-SERVICE")
public interface OrderItemClient {
    @GetMapping("/orderItem/getAllByProductId/{id}")
    ResponseEntity<List<OrderItemDto>> getAllByProductId(@PathVariable("id") Integer productId);
}
