package com.shopping.thirdservice.controller;

import com.shopping.thirdservice.dto.CategoryDto;
import com.shopping.thirdservice.service.CategoryService;
import com.shopping.thirdservice.serviceimpl.CategoryServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/third-service")
@Tag(name = "CATEGORY-APIS")
public class CategoryController implements CategoryService<Integer, CategoryDto> {
    private final CategoryServiceImpl categoryServiceImpl;

    @Override
    @PostMapping("/category/create")
    public ResponseEntity<String> create(@RequestBody CategoryDto dto) {
        return this.categoryServiceImpl.create(dto);
    }

    @Override
    @GetMapping("/category/get/{id}")
    public ResponseEntity<CategoryDto> get(@PathVariable("id") Integer id) {
        return this.categoryServiceImpl.get(id);
    }

    @Override
    @PutMapping("/category/update/{id}")
    public ResponseEntity<String> update(@RequestBody CategoryDto dto, @PathVariable("id") Integer id) {
        return this.categoryServiceImpl.update(dto, id);
    }

    @Override
    @DeleteMapping("/category/delete")
    public ResponseEntity<String> delete(@PathVariable("id") Integer id) {
        return this.categoryServiceImpl.delete(id);
    }

    @Override
    @GetMapping("/category/getAll")
    public ResponseEntity<List<CategoryDto>> getAll() {
        return this.categoryServiceImpl.getAll();
    }

    @Override
    @GetMapping("/category/pages")
    public ResponseEntity<Page<CategoryDto>> getPage(@RequestParam("page") Integer page,
                                                     @RequestParam("size") Integer size) {
        return this.categoryServiceImpl.getPage(page, size);
    }
    @GetMapping("/category/getAllByProducts/{id}")
    public ResponseEntity<CategoryDto> getAllWithProducts(@PathVariable("id") Integer id) {
        return this.categoryServiceImpl.getAllWithProducts(id);
    }
}
