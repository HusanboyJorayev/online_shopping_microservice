package com.shopping.thirdservice.serviceimpl;

import com.shopping.thirdservice.change.ShipmentChange;
import com.shopping.thirdservice.client.OrderClient;
import com.shopping.thirdservice.dto.ShipmentDto;
import com.shopping.thirdservice.dto.WishlistDto;
import com.shopping.thirdservice.entity.Shipment;
import com.shopping.thirdservice.repository.ShipmentRepository;
import com.shopping.thirdservice.service.ShipmentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ShipmentServiceImpl implements ShipmentService<Integer, ShipmentDto> {
    private final ShipmentRepository shipmentRepository;
    private final OrderClient orderClient;
    private final ShipmentChange change;
    private final ModelMapper modelMapper;

    @Override
    public ResponseEntity<String> create(ShipmentDto dto) {
        var shipment = this.change.create(dto);
        this.shipmentRepository.save(shipment);
        return new ResponseEntity<>("created successfully", HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ShipmentDto> get(Integer id) {
        var shipment = this.shipmentRepository.findByIdAndDeletedAtIsNull(id)
                .orElse(new Shipment());
        return new ResponseEntity<>(modelMapper.map(shipment, ShipmentDto.class), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> update(ShipmentDto dto, Integer id) {
        var shipment = this.shipmentRepository.findByIdAndDeletedAtIsNull(id)
                .orElse(new Shipment());
        this.shipmentRepository.save(shipment);
        this.change.update(shipment, dto);
        return new ResponseEntity<>("updated successfully", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> delete(Integer id) {
        var shipment = this.shipmentRepository.findByIdAndDeletedAtIsNull(id)
                .orElse(new Shipment());
        shipment.setDeletedAt(LocalDateTime.now());
        this.shipmentRepository.delete(shipment);
        return new ResponseEntity<>("Deleted successfully", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<ShipmentDto>> getAll() {
        List<Shipment> shipmentList = this.shipmentRepository.findAll();
        if (shipmentList.isEmpty()) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(Arrays.asList(modelMapper.map(shipmentList, ShipmentDto[].class)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Page<ShipmentDto>> getPage(Integer page, Integer size) {
        var shipmentPage = this.shipmentRepository.findAllByDeletedAtIsNull(PageRequest.of(page, size));
        if (shipmentPage.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(shipmentPage.map(shipment -> modelMapper.map(shipment, ShipmentDto.class)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<ShipmentDto>> getAllByCustomerId(Integer customerId) {
        List<Shipment> shipmentList = this.shipmentRepository.findByCustomerIdAndDeletedAtIsNull(customerId).stream().toList();
        if (shipmentList.isEmpty()) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(Arrays.asList(modelMapper.map(shipmentList, ShipmentDto[].class)), HttpStatus.OK);
    }

    public ResponseEntity<ShipmentDto> getAllWithOrder(Integer id) {
        var shipment=this.shipmentRepository.findByIdAndDeletedAtIsNull(id).orElse(new Shipment());
        var dto=modelMapper.map(shipment, ShipmentDto.class);
        dto.setOrders(this.orderClient.getAllByShipmentId(id).getBody());
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }
}
