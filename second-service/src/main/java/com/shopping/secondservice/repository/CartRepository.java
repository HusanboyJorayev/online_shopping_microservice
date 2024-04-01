package com.shopping.secondservice.repository;

import com.shopping.secondservice.entity.Cart;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart,Integer> {

    Optional<Cart> findByIdAndCreatedAtIsNull(Integer integer);
    Optional<Cart> findByCustomerIdAndCreatedAtIsNull(Integer integer);
    Optional<Cart> findByProductIdAndCreatedAtIsNull(Integer integer);


    Page<Cart> findAllByDeletedAtIsNull(Pageable pageable);
}
