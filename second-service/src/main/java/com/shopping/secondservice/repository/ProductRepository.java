package com.shopping.secondservice.repository;

import com.shopping.secondservice.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {

    Optional<Product> findByIdAndDeletedAtIsNull(Integer integer);
    Optional<Product> findByCategoryIdAndCreatedAtIsNull(Integer categoryId);

    Page<Product> findAllByDeletedAtIsNull(Pageable pageable);
}
