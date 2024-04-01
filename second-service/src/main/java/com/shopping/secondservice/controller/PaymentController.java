package com.shopping.secondservice.controller;

import com.shopping.secondservice.dto.PaymentDto;
import com.shopping.secondservice.service.PaymentService;
import com.shopping.secondservice.serviceimpl.PaymentServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/second-service")
public class PaymentController implements PaymentService<Integer, PaymentDto> {
    private final PaymentServiceImpl paymentServiceImpl;

    @Override
    @PostMapping("/payment/create")
    public ResponseEntity<String> create(@RequestBody PaymentDto dto) {
        return this.paymentServiceImpl.create(dto);
    }

    @Override
    @GetMapping("/payment/get/{id}")
    public ResponseEntity<PaymentDto> get(@PathVariable("id") Integer id) {
        return this.paymentServiceImpl.get(id);
    }

    @Override
    @PutMapping("/payment/update/{id}")
    public ResponseEntity<String> update(@RequestBody PaymentDto dto, @PathVariable("id") Integer id) {
        return this.paymentServiceImpl.update(dto, id);
    }

    @Override
    @DeleteMapping("/payment/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Integer id) {
        return this.paymentServiceImpl.delete(id);
    }

    @Override
    @GetMapping("/payment/getAll")
    public ResponseEntity<List<PaymentDto>> getAll() {
        return this.paymentServiceImpl.getAll();
    }

    @Override
    @GetMapping("/payment/pages")
    public ResponseEntity<Page<PaymentDto>> getPage(@RequestParam("page") Integer page,
                                                    @RequestParam("size") Integer size) {
        return this.paymentServiceImpl.getPage(page, size);
    }

    @Override
    @GetMapping("/payment/getAllByCustomerId/{id}")
    public ResponseEntity<List<PaymentDto>> getAllByCustomerId(@PathVariable("id") Integer customerId) {
        return this.paymentServiceImpl.getAllByCustomerId(customerId);
    }
    @GetMapping("/payment/getAllOrders/{id}")
    public ResponseEntity<PaymentDto> getAllWithOrders(@PathVariable("id") Integer id) {
        return this.paymentServiceImpl.getAllWithOrders(id);
    }
}
