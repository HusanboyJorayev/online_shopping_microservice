package com.shopping.firstservice.service;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface OrderItemService<K, V> {
    ResponseEntity<String> create(V dto);

    ResponseEntity<V> get(K id);

    ResponseEntity<String> update(V dto, K id);

    ResponseEntity<String> delete(K id);

    ResponseEntity<List<V>> getAll();

    ResponseEntity<List<V>> getAllByOrderId(Integer orderId);

    ResponseEntity<List<V>> getAllByProductId(Integer productId);

    ResponseEntity<Page<V>> getPage(Integer page, Integer size);
}
