package com.shopping.secondservice.serviceimpl;

import com.shopping.secondservice.change.ProductChange;
import com.shopping.secondservice.client.OrderItemClient;
import com.shopping.secondservice.dto.ProductDto;
import com.shopping.secondservice.entity.Product;
import com.shopping.secondservice.repository.ProductRepository;
import com.shopping.secondservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService<Integer, ProductDto> {
    private final ProductRepository productRepository;
    private final OrderItemClient client;
    private final ModelMapper modelMapper;
    private final ProductChange change;

    @Override
    public ResponseEntity<String> create(ProductDto dto) {
        var product = this.change.create(dto);
        this.productRepository.save(product);
        return new ResponseEntity<>("Crated successfully", HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ProductDto> get(Integer id) {
        var product = this.productRepository.findByIdAndDeletedAtIsNull(id)
                .orElse(new Product());
        return new ResponseEntity<>(modelMapper.map(product, ProductDto.class), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> update(ProductDto dto, Integer id) {
        var product = this.productRepository.findByIdAndDeletedAtIsNull(id)
                .orElse(new Product());
        var updateCart = this.change.update(product, dto);
        this.productRepository.save(updateCart);
        return new ResponseEntity<>("Updated successfully", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> delete(Integer id) {
        var product = this.productRepository.findByIdAndDeletedAtIsNull(id)
                .orElse(new Product());
        product.setDeletedAt(LocalDateTime.now());
        this.productRepository.delete(product);
        return new ResponseEntity<>("Deleted successfully", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<ProductDto>> getAll() {
        List<Product> productList = this.productRepository.findAll();
        if (productList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(Arrays.asList(modelMapper.map(productList, ProductDto[].class)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Page<ProductDto>> getPage(Integer page, Integer size) {
        var productPage = this.productRepository.findAllByDeletedAtIsNull(PageRequest.of(page, size));
        if (productPage.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(productPage.map(product -> modelMapper.map(product, ProductDto.class)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<ProductDto>> getAllByCategoryId(Integer categoryId) {
        List<Product> categoryList = this.productRepository.findByCategoryIdAndCreatedAtIsNull(categoryId).stream().toList();
        if (categoryList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(Arrays.asList(modelMapper.map(categoryList, ProductDto[].class)), HttpStatus.OK);
    }

    public ResponseEntity<ProductDto> getAllWithOrderItems(Integer id) {
        var product = this.productRepository.findByIdAndDeletedAtIsNull(id).orElse(new Product());
        var dto = modelMapper.map(product, ProductDto.class);
        dto.setOrderItems(this.client.getAllByProductId(id).getBody());
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
