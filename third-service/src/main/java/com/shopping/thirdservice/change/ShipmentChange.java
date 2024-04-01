package com.shopping.thirdservice.change;

import com.shopping.thirdservice.dto.ShipmentDto;
import com.shopping.thirdservice.entity.Shipment;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ShipmentChange {
    public Shipment create(ShipmentDto dto) {
        return Shipment.builder()
                .address(dto.getAddress())
                .date(LocalDateTime.now())
                .city(dto.getCity())
                .country(dto.getCountry())
                .state(dto.getState())
                .customerId(dto.getCustomerId())
                .zipCode(dto.getZipCode())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public Shipment update(Shipment shipment, ShipmentDto dto) {
        shipment.setAddress(dto.getAddress());
        shipment.setDate(LocalDateTime.now());
        shipment.setCity(dto.getCity());
        shipment.setCountry(dto.getCountry());
        shipment.setState(dto.getState());
        shipment.setCustomerId(dto.getCustomerId());
        shipment.setZipCode(dto.getZipCode());
        shipment.setUpdatedAt(LocalDateTime.now());
        return shipment;
    }
}
