package com.shopping.secondservice.client;

import com.shopping.secondservice.client.dto.OrderDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient("FIRST-SERVICE")
public interface OrderClient {
    @GetMapping("/order/getAllByPaymentId/{id}")
    ResponseEntity<List<OrderDto>> getAllByPaymentId(@PathVariable("id") Integer paymentId);
}
