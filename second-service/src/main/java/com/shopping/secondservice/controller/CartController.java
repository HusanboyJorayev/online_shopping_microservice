package com.shopping.secondservice.controller;

import com.shopping.secondservice.dto.CartDto;
import com.shopping.secondservice.service.CartService;
import com.shopping.secondservice.serviceimpl.CartServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/second-service")
public class CartController implements CartService<Integer, CartDto> {
    private final CartServiceImpl cartServiceImpl;

    @Override
    @PostMapping("/cart/create")
    public ResponseEntity<String> create(@RequestBody CartDto dto) {
        return this.cartServiceImpl.create(dto);
    }

    @Override
    @GetMapping("/cart/get/{id}")
    public ResponseEntity<CartDto> get(@PathVariable("id") Integer id) {
        return this.cartServiceImpl.get(id);
    }

    @Override
    @PutMapping("/cart/update/{id}")
    public ResponseEntity<String> update(@RequestBody CartDto dto, @PathVariable("id") Integer id) {
        return this.cartServiceImpl.update(dto, id);
    }

    @Override
    @DeleteMapping("/cart/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Integer id) {
        return this.cartServiceImpl.delete(id);
    }

    @Override
    @GetMapping("/cart/getAll")
    public ResponseEntity<List<CartDto>> getAll() {
        return this.cartServiceImpl.getAll();
    }

    @Override
    @GetMapping("/cart/pages")
    public ResponseEntity<Page<CartDto>> getPage(@RequestParam("page") Integer page,
                                                 @RequestParam("size") Integer size) {
        return this.cartServiceImpl.getPage(page, size);
    }

    @Override
    @GetMapping("/cart/getByCustomerId/(id}")
    public ResponseEntity<List<CartDto>> getAllCartByCustomerId(@PathVariable("id") Integer customerId) {
        return this.cartServiceImpl.getAllCartByCustomerId(customerId);
    }

    @Override
    @GetMapping("/cart/getByProductId/(id}")
    public ResponseEntity<List<CartDto>> getAllCartByProductId(@PathVariable("id") Integer productId) {
        return this.cartServiceImpl.getAllCartByProductId(productId);
    }
}
