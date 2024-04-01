package com.shopping.secondservice.repository;

import com.shopping.secondservice.entity.Payment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,Integer> {

    Optional<Payment> findByIdAndDeletedAtIsNull(Integer integer);
    Optional<Payment> findByCustomerIdAndCreatedAtIsNull(Integer customerId);

    Page<Payment> findAllByDeletedAtIsNull(Pageable pageable);
}
