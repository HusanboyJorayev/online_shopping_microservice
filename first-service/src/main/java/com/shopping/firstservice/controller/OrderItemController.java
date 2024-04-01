package com.shopping.firstservice.controller;

import com.shopping.firstservice.dto.OrderItemDto;
import com.shopping.firstservice.service.OrderItemService;
import com.shopping.firstservice.serviceimpl.OrderItemServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/first-service")
@Tag(name = "ORDER ITEM CONTROLLER")
public class OrderItemController implements OrderItemService<Integer, OrderItemDto> {
    private final OrderItemServiceImpl orderItemServiceImpl;

    @Override
    @PostMapping("/orderItem")
    public ResponseEntity<String> create(@RequestBody OrderItemDto dto) {
        return this.orderItemServiceImpl.create(dto);
    }

    @Override
    @GetMapping("/orderItem/{id}")
    public ResponseEntity<OrderItemDto> get(@PathVariable("id") Integer id) {
        return this.orderItemServiceImpl.get(id);
    }

    @Override
    @PutMapping("/orderItem/update/{id}")
    public ResponseEntity<String> update(@RequestBody OrderItemDto dto, @PathVariable("id") Integer id) {
        return this.orderItemServiceImpl.update(dto, id);
    }

    @Override
    @DeleteMapping("/orderItem/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Integer id) {
        return this.orderItemServiceImpl.delete(id);
    }

    @Override
    @GetMapping("/orderItems")
    public ResponseEntity<List<OrderItemDto>> getAll() {
        return this.orderItemServiceImpl.getAll();
    }

    @Override
    @GetMapping("/orderItem/pages")
    public ResponseEntity<Page<OrderItemDto>> getPage(@RequestParam("page") Integer page,
                                                      @RequestParam("size") Integer size) {
        return this.orderItemServiceImpl.getPage(page, size);
    }

    @Override
    @GetMapping("/orderItem/getAllByOrderId/{id}")
    public ResponseEntity<List<OrderItemDto>> getAllByOrderId(@PathVariable("id") Integer orderId) {
        return this.orderItemServiceImpl.getAllByOrderId(orderId);
    }

    @Override
    @GetMapping("/orderItem/getAllByProductId/{id}")
    public ResponseEntity<List<OrderItemDto>> getAllByProductId(@PathVariable("id") Integer productId) {
        return this.orderItemServiceImpl.getAllByProductId(productId);
    }
}

