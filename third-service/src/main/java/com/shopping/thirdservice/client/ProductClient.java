package com.shopping.thirdservice.client;

import com.shopping.thirdservice.client.dto.ProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient("SECOND-SERVICE")
public interface ProductClient {
    @GetMapping("/product/getAllByCategoryId/{id}")
    ResponseEntity<List<ProductDto>> getAllByCategoryId(@PathVariable("id") Integer categoryId);
}
