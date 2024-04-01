package com.shopping.firstservice.repository;

import com.shopping.firstservice.entity.Customer;
import com.shopping.firstservice.entity.Order;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Optional<Customer> findByIdAndDeletedAtIsNull(Integer id);

    Page<Customer>findAllByDeletedAtIsNull(Pageable pageable);
}
