package com.shopping.firstservice.controller;

import com.shopping.firstservice.dto.OrderDto;
import com.shopping.firstservice.service.OrderService;
import com.shopping.firstservice.serviceimpl.OrderServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/first-service")
@Tag(name = "ORDER CONTROLLER")
public class OrderController implements OrderService<Integer, OrderDto> {
    private final OrderServiceImpl orderServiceImpl;

    @Override
    @PostMapping("/order")
    public ResponseEntity<String> create(@RequestBody OrderDto dto) {
        return this.orderServiceImpl.create(dto);
    }

    @Override
    @GetMapping("/order/{id}")
    public ResponseEntity<OrderDto> get(@PathVariable("id") Integer id) {
        return this.orderServiceImpl.get(id);
    }

    @Override
    @PutMapping("/order/update/{id}")
    public ResponseEntity<String> update(@RequestBody OrderDto dto, @PathVariable("id") Integer id) {
        return this.orderServiceImpl.update(dto, id);
    }

    @Override
    @DeleteMapping("/order/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Integer id) {
        return this.orderServiceImpl.delete(id);
    }

    @Override
    @GetMapping("/orders")
    public ResponseEntity<List<OrderDto>> getAll() {
        return this.orderServiceImpl.getAll();
    }

    @Override
    @GetMapping("/order/pages")
    public ResponseEntity<Page<OrderDto>> getPage(@RequestParam("page") Integer page,
                                                  @RequestParam("size") Integer size) {
        return this.orderServiceImpl.getPage(page, size);
    }

    @Override
    @GetMapping("/order/getAllByCustomerId/{id}")
    public ResponseEntity<List<OrderDto>> getAllByCustomerId(@PathVariable("id") Integer customerId) {
        return this.orderServiceImpl.getAllByCustomerId(customerId);
    }

    @Override
    @GetMapping("/order/getAllByShipmentId/{id}")
    public ResponseEntity<List<OrderDto>> getAllByShipmentId(@PathVariable("id") Integer shipmentId) {
        return this.orderServiceImpl.getAllByShipmentId(shipmentId);
    }

    @Override
    @GetMapping("/order/getAllByPaymentId/{id}")
    public ResponseEntity<List<OrderDto>> getAllByPaymentId(@PathVariable("id") Integer paymentId) {
        return this.orderServiceImpl.getAllByPaymentId(paymentId);
    }
}

