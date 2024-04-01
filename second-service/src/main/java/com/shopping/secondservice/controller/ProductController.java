package com.shopping.secondservice.controller;

import com.shopping.secondservice.dto.ProductDto;
import com.shopping.secondservice.service.ProductService;
import com.shopping.secondservice.serviceimpl.ProductServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/second-service")
public class ProductController implements ProductService<Integer, ProductDto> {
    private final ProductServiceImpl productServiceIMpl;

    @Override
    @PostMapping("/product/create")
    public ResponseEntity<String> create(@RequestBody ProductDto dto) {
        return this.productServiceIMpl.create(dto);
    }

    @Override
    @GetMapping("/product/get/{id}")
    public ResponseEntity<ProductDto> get(@PathVariable("id") Integer id) {
        return this.productServiceIMpl.get(id);
    }

    @Override
    @PutMapping("/product/update/{id}")
    public ResponseEntity<String> update(@RequestBody ProductDto dto, @PathVariable("id") Integer id) {
        return this.productServiceIMpl.update(dto, id);
    }

    @Override
    @DeleteMapping("/product/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Integer id) {
        return this.productServiceIMpl.delete(id);
    }

    @Override
    @GetMapping("/product/getAll")
    public ResponseEntity<List<ProductDto>> getAll() {
        return this.productServiceIMpl.getAll();
    }

    @Override
    @GetMapping("/product/pages")
    public ResponseEntity<Page<ProductDto>> getPage(@RequestParam("page") Integer page,
                                                    @RequestParam("size") Integer size) {
        return this.productServiceIMpl.getPage(page, size);
    }

    @Override
    @GetMapping("/product/getAllByCategoryId/{id}")
    public ResponseEntity<List<ProductDto>> getAllByCategoryId(@PathVariable("id") Integer categoryId) {
        return this.productServiceIMpl.getAllByCategoryId(categoryId);
    }
    @GetMapping("/product/getAllByOrderItems/{id}")
    public ResponseEntity<ProductDto> getAllWithOrderItems(@PathVariable("id") Integer id) {
        return this.productServiceIMpl.getAllWithOrderItems(id);
    }
}
