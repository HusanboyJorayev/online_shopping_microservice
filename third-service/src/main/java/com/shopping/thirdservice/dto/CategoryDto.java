package com.shopping.thirdservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.shopping.thirdservice.client.dto.ProductDto;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoryDto {
    private Integer id;
    private String name;

    private List<ProductDto>products;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
