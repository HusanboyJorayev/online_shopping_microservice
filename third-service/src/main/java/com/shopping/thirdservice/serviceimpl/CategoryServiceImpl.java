package com.shopping.thirdservice.serviceimpl;

import com.shopping.thirdservice.change.CategoryChange;
import com.shopping.thirdservice.client.ProductClient;
import com.shopping.thirdservice.dto.CategoryDto;
import com.shopping.thirdservice.entity.Category;
import com.shopping.thirdservice.repository.CategoryRepository;
import com.shopping.thirdservice.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService<Integer, CategoryDto> {
    private final CategoryRepository categoryRepository;
    private final ProductClient productClient;
    private final CategoryChange change;
    private final ModelMapper modelMapper;

    @Override
    public ResponseEntity<String> create(CategoryDto dto) {
        var category = this.change.create(dto);
        this.categoryRepository.save(category);
        return new ResponseEntity<>("created successfully", HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<CategoryDto> get(Integer id) {
        var category = this.categoryRepository.findByIdAndDeletedAtIsNull(id)
                .orElse(new Category());
        return new ResponseEntity<>(modelMapper.map(category, CategoryDto.class), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> update(CategoryDto dto, Integer id) {
        var category = this.categoryRepository.findByIdAndDeletedAtIsNull(id)
                .orElse(new Category());
        this.categoryRepository.save(category);
        this.change.update(category, dto);
        return new ResponseEntity<>("updated successfully", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> delete(Integer id) {
        var category = this.categoryRepository.findByIdAndDeletedAtIsNull(id)
                .orElse(new Category());
        category.setDeletedAt(LocalDateTime.now());
        this.categoryRepository.delete(category);
        return new ResponseEntity<>("Deleted successfully", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<CategoryDto>> getAll() {
        List<Category> categoryList = this.categoryRepository.findAll();
        if (categoryList.isEmpty()) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(Arrays.asList(modelMapper.map(categoryList, CategoryDto[].class)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Page<CategoryDto>> getPage(Integer page, Integer size) {
        var categoryPage = this.categoryRepository.findAllByDeletedAtIsNull(PageRequest.of(page, size));
        if (categoryPage.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(categoryPage.map(category -> modelMapper.map(category, CategoryDto.class)), HttpStatus.OK);
    }

    public ResponseEntity<CategoryDto> getAllWithProducts(Integer id) {
        var category = this.categoryRepository.findByIdAndDeletedAtIsNull(id).orElse(new Category());
        var dto = modelMapper.map(category, CategoryDto.class);
        dto.setProducts(this.productClient.getAllByCategoryId(id).getBody());
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
