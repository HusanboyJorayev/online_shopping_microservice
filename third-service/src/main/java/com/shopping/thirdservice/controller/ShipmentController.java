package com.shopping.thirdservice.controller;

import com.shopping.thirdservice.dto.ShipmentDto;
import com.shopping.thirdservice.dto.WishlistDto;
import com.shopping.thirdservice.service.ShipmentService;
import com.shopping.thirdservice.serviceimpl.ShipmentServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.MediaSize;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/third-service")
@Tag(name = "SHIPMENT CONTROLLER")
public class ShipmentController implements ShipmentService<Integer, ShipmentDto> {
    private final ShipmentServiceImpl shipmentServiceImpl;

    @Override
    @PostMapping("/shipment/create")
    public ResponseEntity<String> create(@RequestBody ShipmentDto dto) {
        return this.shipmentServiceImpl.create(dto);
    }

    @Override
    @GetMapping("/shipment/get/{id}")
    public ResponseEntity<ShipmentDto> get(@PathVariable("id") Integer id) {
        return this.shipmentServiceImpl.get(id);
    }

    @Override
    @PutMapping("/shipment/update/{id}")
    public ResponseEntity<String> update(@RequestBody ShipmentDto dto, @PathVariable("id") Integer id) {
        return this.shipmentServiceImpl.update(dto, id);
    }

    @Override
    @DeleteMapping("/shipment/delete")
    public ResponseEntity<String> delete(@PathVariable("id") Integer id) {
        return this.shipmentServiceImpl.delete(id);
    }

    @Override
    @GetMapping("/shipment/getAll")
    public ResponseEntity<List<ShipmentDto>> getAll() {
        return this.shipmentServiceImpl.getAll();
    }

    @Override
    @GetMapping("/shipment/pages")
    public ResponseEntity<Page<ShipmentDto>> getPage(@RequestParam("page") Integer page,
                                                     @RequestParam("size") Integer size) {
        return this.shipmentServiceImpl.getPage(page, size);
    }

    @Override
    @GetMapping("/shipment/getAllByCustomerId/{id}")
    public ResponseEntity<List<ShipmentDto>> getAllByCustomerId(@PathVariable("id") Integer customerId) {
        return this.shipmentServiceImpl.getAllByCustomerId(customerId);
    }

    @GetMapping("/wishlist/getAllWithOrder/{id}")
    public ResponseEntity<ShipmentDto> getAllWithOrder(@PathVariable("id") Integer id) {
        return this.shipmentServiceImpl.getAllWithOrder(id);
    }
}
