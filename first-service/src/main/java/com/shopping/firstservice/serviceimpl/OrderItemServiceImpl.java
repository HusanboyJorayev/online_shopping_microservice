package com.shopping.firstservice.serviceimpl;

import com.shopping.firstservice.change.ExchangeOrderItem;
import com.shopping.firstservice.dto.OrderDto;
import com.shopping.firstservice.dto.OrderItemDto;
import com.shopping.firstservice.entity.Order;
import com.shopping.firstservice.entity.OrderItem;
import com.shopping.firstservice.repository.OrderItemRepository;
import com.shopping.firstservice.service.OrderItemService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderItemServiceImpl implements OrderItemService<Integer, OrderItemDto> {
    private final OrderItemRepository orderItemRepository;
    private final ModelMapper modelMapper;
    private final ExchangeOrderItem exchangeOrderItem;

    @Override
    public ResponseEntity<String> create(OrderItemDto dto) {
        var orderItem = this.exchangeOrderItem.create(dto);
        this.orderItemRepository.save(orderItem);
        return new ResponseEntity<>("Created successfully", HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<OrderItemDto> get(Integer id) {
        var orderItem = this.orderItemRepository.findByIdAndDeletedAtIsNull(id)
                .orElse(null);
        return new ResponseEntity<>(modelMapper.map(orderItem, OrderItemDto.class), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> update(OrderItemDto dto, Integer id) {
        var orderItem = this.orderItemRepository.findByIdAndDeletedAtIsNull(id)
                .orElse(new OrderItem());
        var newOrder = this.exchangeOrderItem.update(orderItem, dto);
        this.orderItemRepository.save(newOrder);
        return new ResponseEntity<>("Updated successfully", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> delete(Integer id) {
        var orderItem = this.orderItemRepository.findByIdAndDeletedAtIsNull(id)
                .orElse(new OrderItem());
        orderItem.setDeletedAt(LocalDateTime.now());
        this.orderItemRepository.delete(orderItem);
        return new ResponseEntity<>("Deleted successfully", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<OrderItemDto>> getAll() {
        List<OrderItem> orderItemList = this.orderItemRepository.findAll();
        if (orderItemList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(Arrays.asList(modelMapper.map(orderItemList, OrderItemDto[].class))
                , HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Page<OrderItemDto>> getPage(Integer page, Integer size) {
        Page<OrderItem> orderItemPage = this.orderItemRepository.findAllByDeletedAtIsNull(PageRequest.of(page, size));
        if (orderItemPage.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(orderItemPage.map(orderItem -> modelMapper.map(orderItem, OrderItemDto.class))
                , HttpStatus.OK);
    }
}
