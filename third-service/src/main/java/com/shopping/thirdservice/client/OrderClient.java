package com.shopping.thirdservice.client;

import com.shopping.thirdservice.client.dto.OrderDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient("FIRST-SERVICE")
public interface OrderClient {
    @GetMapping("/order/getAllByShipmentId/{id}")
    ResponseEntity<List<OrderDto>> getAllByShipmentId(@PathVariable("id") Integer shipmentId);
}
