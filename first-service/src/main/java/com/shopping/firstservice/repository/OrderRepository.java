package com.shopping.firstservice.repository;

import com.shopping.firstservice.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    Optional<Order> findByIdAndDeletedAtIsNull(Integer id);

    Optional<Order> findByCustomerIdAndDeletedAtIsNull(Integer customerId);

    Optional<Order> findByShipmentIdAndDeletedAtIsNull(Integer shipmentId);

    Optional<Order> findByPaymentIdAndDeletedAtIsNull(Integer paymentId);

    Page<Order> findAllByDeletedAtIsNull(Pageable pageable);
}
