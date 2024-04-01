package com.shopping.firstservice.serviceimpl;

import com.shopping.firstservice.change.ExchangeOrder;
import com.shopping.firstservice.dto.CustomerDto;
import com.shopping.firstservice.dto.OrderDto;
import com.shopping.firstservice.entity.Customer;
import com.shopping.firstservice.entity.Order;
import com.shopping.firstservice.repository.OrderRepository;
import com.shopping.firstservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService<Integer, OrderDto> {
    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;
    private final ExchangeOrder exchangeOrder;

    @Override
    public ResponseEntity<String> create(OrderDto dto) {
        var order = this.exchangeOrder.create(dto);
        this.orderRepository.save(order);
        return new ResponseEntity<>("Created successfully", HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<OrderDto> get(Integer id) {
        var order = this.orderRepository.findByIdAndDeletedAtIsNull(id)
                .orElse(null);
        return new ResponseEntity<>(modelMapper.map(order, OrderDto.class), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> update(OrderDto dto, Integer id) {
        var order = this.orderRepository.findByIdAndDeletedAtIsNull(id)
                .orElse(new Order());
        var newOrder = this.exchangeOrder.update(order, dto);
        this.orderRepository.save(newOrder);
        return new ResponseEntity<>("Updated successfully", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> delete(Integer id) {
        var order = this.orderRepository.findByIdAndDeletedAtIsNull(id)
                .orElse(new Order());
        order.setDeletedAt(LocalDateTime.now());
        this.orderRepository.delete(order);
        return new ResponseEntity<>("Deleted successfully", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<OrderDto>> getAll() {
        List<Order> orderList = this.orderRepository.findAll();
        if (orderList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(Arrays.asList(modelMapper.map(orderList, OrderDto[].class))
                , HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Page<OrderDto>> getPage(Integer page, Integer size) {
        Page<Order> orderPage = this.orderRepository.findAllByDeletedAtIsNull(PageRequest.of(page, size));
        if (orderPage.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(orderPage.map(orders -> modelMapper.map(orders, OrderDto.class)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<OrderDto>> getAllByCustomerId(Integer customerId) {
        List<Order> orderList = this.orderRepository.findByCustomerIdAndDeletedAtIsNull(customerId).stream().toList();
        if (orderList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(Arrays.asList(modelMapper.map(orderList, OrderDto[].class))
                , HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<OrderDto>> getAllByShipmentId(Integer shipmentId) {
        List<Order> orderList = this.orderRepository.findByShipmentIdAndDeletedAtIsNull(shipmentId).stream().toList();
        if (orderList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(Arrays.asList(modelMapper.map(orderList, OrderDto[].class))
                , HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<OrderDto>> getAllByPaymentId(Integer paymentId) {
        List<Order> orderList = this.orderRepository.findByPaymentIdAndDeletedAtIsNull(paymentId).stream().toList();
        if (orderList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(Arrays.asList(modelMapper.map(orderList, OrderDto[].class))
                , HttpStatus.OK);
    }
}
