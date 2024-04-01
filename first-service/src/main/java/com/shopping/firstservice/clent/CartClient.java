package com.shopping.firstservice.clent;

import com.shopping.firstservice.clent.dto.CartDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient("SECOND-SERVICE")
public interface CartClient {
    @GetMapping("/cart/getByCustomerId/{id}")
    ResponseEntity<List<CartDto>> getAllCartByCustomerId(@PathVariable("id") Integer customerId);
}
