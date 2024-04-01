package com.shopping.firstservice.clent;

import com.shopping.firstservice.clent.dto.PaymentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient("SECOND-SERVICE")
public interface PaymentClient {
    @GetMapping("/payment/getAllByCustomerId/{id}")
    ResponseEntity<List<PaymentDto>> getAllByCustomerId(@PathVariable("id") Integer customerId);
}
