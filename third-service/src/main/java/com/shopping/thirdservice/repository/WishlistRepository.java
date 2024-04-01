package com.shopping.thirdservice.repository;

import com.shopping.thirdservice.entity.Wishlist;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist,Integer> {

    Optional<Wishlist> findByIdAndDeletedAtIsNull(Integer integer);


    Page<Wishlist> findAllByDeletedAtIsNull(Pageable pageable);
}
