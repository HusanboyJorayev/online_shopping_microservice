package com.shopping.thirdservice.controller;

import com.shopping.thirdservice.dto.WishlistDto;
import com.shopping.thirdservice.service.WishlistService;
import com.shopping.thirdservice.serviceimpl.WishlistServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/third-service")
@Tag(name = "WISHLIST CONTROLLER")
public class WishlistController implements WishlistService<Integer, WishlistDto> {
    private final WishlistServiceImpl wishlistServiceImpl;

    @Override
    @PostMapping("/wishlist/create")
    public ResponseEntity<String> create(@RequestBody WishlistDto dto) {
        return this.wishlistServiceImpl.create(dto);
    }

    @Override
    @GetMapping("/wishlist/get/{id}")
    public ResponseEntity<WishlistDto> get(@PathVariable("id") Integer id) {
        return this.wishlistServiceImpl.get(id);
    }

    @Override
    @PutMapping("/wishlist/update/{id}")
    public ResponseEntity<String> update(@RequestBody WishlistDto dto, @PathVariable("id") Integer id) {
        return this.wishlistServiceImpl.update(dto, id);
    }

    @Override
    @DeleteMapping("/wishlist/delete")
    public ResponseEntity<String> delete(@PathVariable("id") Integer id) {
        return this.wishlistServiceImpl.delete(id);
    }

    @Override
    @GetMapping("/wishlist/getAll")
    public ResponseEntity<List<WishlistDto>> getAll() {
        return this.wishlistServiceImpl.getAll();
    }

    @Override
    @GetMapping("/wishlist/pages")
    public ResponseEntity<Page<WishlistDto>> getPage(@RequestParam("page") Integer page,
                                                     @RequestParam("size") Integer size) {
        return this.wishlistServiceImpl.getPage(page, size);
    }
}
