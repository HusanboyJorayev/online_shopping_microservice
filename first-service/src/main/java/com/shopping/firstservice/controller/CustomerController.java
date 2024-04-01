package com.shopping.firstservice.controller;

import com.shopping.firstservice.dto.CustomerDto;
import com.shopping.firstservice.service.CustomerService;
import com.shopping.firstservice.serviceimpl.CustomerServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/first-service")
@Tag(name = "CUSTOMER CONTROLLER")
public class CustomerController implements CustomerService<Integer, CustomerDto> {
    private final CustomerServiceImpl customerServiceImpl;

    @Override
    @PostMapping("/customer")
    public ResponseEntity<String> create(@RequestBody CustomerDto dto) {
        return this.customerServiceImpl.create(dto);
    }

    @Override
    @GetMapping("/customer/{id}")
    public ResponseEntity<CustomerDto> get(@PathVariable("id") Integer id) {
        return this.customerServiceImpl.get(id);
    }

    @Override
    @PutMapping("/customer/update/{id}")
    public ResponseEntity<String> update(@RequestBody CustomerDto dto, @PathVariable("id") Integer id) {
        return this.customerServiceImpl.update(dto, id);
    }

    @Override
    @DeleteMapping("/customer/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Integer id) {
        return this.customerServiceImpl.delete(id);
    }

    @Override
    @GetMapping("/customers")
    public ResponseEntity<List<CustomerDto>> getAll() {
        return this.customerServiceImpl.getAll();
    }

    @Override
    @GetMapping("/customer/pages")
    public ResponseEntity<Page<CustomerDto>> getPage(@RequestParam("page") Integer page, @RequestParam("size") Integer size) {
        return this.customerServiceImpl.getPage(page, size);
    }

    @GetMapping("/customer/getAllWithCart/{id}")
    public ResponseEntity<CustomerDto>getAllWithCart(@PathVariable("id") Integer id) {
        return this.customerServiceImpl.getAllWithCart(id);
    }
    @GetMapping("/customer/getAllWithPayment/{id}")
    public ResponseEntity<CustomerDto>getAllWithPayment(@PathVariable("id") Integer id) {
        return this.customerServiceImpl.getAllWithPayment(id);
    }
}

