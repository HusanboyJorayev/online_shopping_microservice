package com.shopping.thirdservice.serviceimpl;


import com.shopping.thirdservice.change.WishlistChange;
import com.shopping.thirdservice.client.OrderClient;
import com.shopping.thirdservice.dto.WishlistDto;
import com.shopping.thirdservice.entity.Wishlist;
import com.shopping.thirdservice.repository.WishlistRepository;
import com.shopping.thirdservice.service.WishlistService;
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
public class WishlistServiceImpl implements WishlistService<Integer, WishlistDto> {
    private final WishlistRepository wishlistRepository;
    private final WishlistChange change;
    private final ModelMapper modelMapper;

    @Override
    public ResponseEntity<String> create(WishlistDto dto) {
        var wishlist = this.change.create(dto);
        this.wishlistRepository.save(wishlist);
        return new ResponseEntity<>("created successfully", HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<WishlistDto> get(Integer id) {
        var wishlist = this.wishlistRepository.findByIdAndDeletedAtIsNull(id)
                .orElse(new Wishlist());
        return new ResponseEntity<>(modelMapper.map(wishlist, WishlistDto.class), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> update(WishlistDto dto, Integer id) {
        var wishlist = this.wishlistRepository.findByIdAndDeletedAtIsNull(id)
                .orElse(new Wishlist());
        this.wishlistRepository.save(wishlist);
        this.change.update(wishlist, dto);
        return new ResponseEntity<>("updated successfully", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> delete(Integer id) {
        var wishlist = this.wishlistRepository.findByIdAndDeletedAtIsNull(id)
                .orElse(new Wishlist());
        wishlist.setDeletedAt(LocalDateTime.now());
        this.wishlistRepository.delete(wishlist);
        return new ResponseEntity<>("Deleted successfully", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<WishlistDto>> getAll() {
        List<Wishlist> wishListList = this.wishlistRepository.findAll();
        if (wishListList.isEmpty()) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(Arrays.asList(modelMapper.map(wishListList, WishlistDto[].class)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Page<WishlistDto>> getPage(Integer page, Integer size) {
        var wishListPage = this.wishlistRepository.findAllByDeletedAtIsNull(PageRequest.of(page, size));
        if (wishListPage.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(wishListPage.map(wishlist -> modelMapper.map(wishlist, WishlistDto.class)), HttpStatus.OK);
    }

}
