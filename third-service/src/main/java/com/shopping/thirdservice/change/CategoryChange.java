package com.shopping.thirdservice.change;

import com.shopping.thirdservice.dto.CategoryDto;
import com.shopping.thirdservice.entity.Category;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CategoryChange {
    public Category create(CategoryDto dto) {
        return Category.builder()
                .name(dto.getName())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public Category update(Category category, CategoryDto dto) {
        category.setName(dto.getName());
        category.setUpdatedAt(LocalDateTime.now());
        return category;
    }
}
