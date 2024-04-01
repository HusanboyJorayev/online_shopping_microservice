package com.shopping.firstservice.repository;


import com.shopping.firstservice.entity.OrderItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
    Optional<OrderItem> findByIdAndDeletedAtIsNull(Integer id);

    Optional<OrderItem> findByOrderIdAndDeletedAtIsNull(Integer orderId);

    Optional<OrderItem> findByProductIdAndDeletedAtIsNull(Integer productId);

    Page<OrderItem> findAllByDeletedAtIsNull(Pageable pageable);
}
