package com.shopping.secondservice.serviceimpl;

import com.shopping.secondservice.change.CartChange;
import com.shopping.secondservice.dto.CartDto;
import com.shopping.secondservice.entity.Cart;
import com.shopping.secondservice.repository.CartRepository;
import com.shopping.secondservice.service.CartService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService<Integer, CartDto> {
    private final CartRepository cartRepository;
    private final ModelMapper modelMapper;
    private final CartChange change;

    @Override
    public ResponseEntity<String> create(CartDto dto) {
        var cart = this.change.create(dto);
        this.cartRepository.save(cart);
        return new ResponseEntity<>("Crated successfully", HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<CartDto> get(Integer id) {
        var cart = this.cartRepository.findByIdAndCreatedAtIsNull(id)
                .orElse(new Cart());
        return new ResponseEntity<>(modelMapper.map(cart, CartDto.class), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> update(CartDto dto, Integer id) {
        var cart = this.cartRepository.findByIdAndCreatedAtIsNull(id)
                .orElse(new Cart());
        var updateCart = this.change.update(dto, cart);
        this.cartRepository.save(updateCart);
        return new ResponseEntity<>("Updated successfully", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> delete(Integer id) {
        var cart = this.cartRepository.findByIdAndCreatedAtIsNull(id)
                .orElse(new Cart());
        cart.setDeletedAt(LocalDateTime.now());
        this.cartRepository.delete(cart);
        return new ResponseEntity<>("Deleted successfully", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<CartDto>> getAll() {
        List<Cart> cartList = this.cartRepository.findAll();
        if (cartList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(Arrays.asList(modelMapper.map(cartList, CartDto[].class)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Page<CartDto>> getPage(Integer page, Integer size) {
        var cartPage = this.cartRepository.findAllByDeletedAtIsNull(PageRequest.of(page, size));
        if (cartPage.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(cartPage.map(cart -> modelMapper.map(cart, CartDto.class)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<CartDto>> getAllCartByCustomerId(Integer customerId) {
        List<Cart> cartList = this.cartRepository.findByCustomerIdAndCreatedAtIsNull(customerId).stream().toList();
        if (cartList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(Arrays.asList(modelMapper.map(cartList, CartDto[].class)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<CartDto>> getAllCartByProductId(Integer productId) {
        List<Cart> productList = this.cartRepository.findByProductIdAndCreatedAtIsNull(productId).stream().toList();
        if (productList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(Arrays.asList(modelMapper.map(productList, CartDto[].class)), HttpStatus.OK);
    }
}
