package com.shopping.secondservice.change;

import com.shopping.secondservice.dto.ProductDto;
import com.shopping.secondservice.entity.Product;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ProductChange {
    public Product create(ProductDto dto) {
        return Product.builder()
                .categoryId(dto.getCategoryId())
                .sku(dto.getSku())
                .price(dto.getPrice())
                .stock(dto.getStock())
                .description(dto.getDescription())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public Product update(Product product, ProductDto dto) {
        product.setCategoryId(dto.getCategoryId());
        product.setPrice(dto.getPrice());
        product.setSku(dto.getSku());
        product.setDescription(dto.getDescription());
        product.setStock(dto.getStock());
        product.setUpdatedAt(LocalDateTime.now());
        return product;
    }
}
