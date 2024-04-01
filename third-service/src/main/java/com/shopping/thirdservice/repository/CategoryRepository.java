package com.shopping.thirdservice.repository;

import com.shopping.thirdservice.entity.Category;
import com.shopping.thirdservice.entity.Wishlist;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    Optional<Category> findByIdAndDeletedAtIsNull(Integer integer);


    Page<Category> findAllByDeletedAtIsNull(Pageable pageable);
}
