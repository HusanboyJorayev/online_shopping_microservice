package com.shopping.thirdservice.repository;

import com.shopping.thirdservice.entity.Shipment;
import com.shopping.thirdservice.entity.Wishlist;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShipmentRepository extends JpaRepository<Shipment,Integer> {

    Optional<Shipment> findByIdAndDeletedAtIsNull(Integer id);
    Optional<Shipment> findByCustomerIdAndDeletedAtIsNull(Integer customerId);

    Page<Shipment> findAllByDeletedAtIsNull(Pageable pageable);
}
