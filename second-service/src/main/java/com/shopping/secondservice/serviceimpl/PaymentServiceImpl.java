package com.shopping.secondservice.serviceimpl;

import com.shopping.secondservice.change.PaymentChange;
import com.shopping.secondservice.client.OrderClient;
import com.shopping.secondservice.dto.PaymentDto;
import com.shopping.secondservice.entity.Payment;
import com.shopping.secondservice.repository.PaymentRepository;
import com.shopping.secondservice.service.CartService;
import com.shopping.secondservice.service.PaymentService;
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
public class PaymentServiceImpl implements PaymentService<Integer, PaymentDto> {
    private final PaymentRepository paymentRepository;
    private final ModelMapper modelMapper;
    private final OrderClient orderClient;
    private final PaymentChange change;

    @Override
    public ResponseEntity<String> create(PaymentDto dto) {
        var payment = this.change.create(dto);
        this.paymentRepository.save(payment);
        return new ResponseEntity<>("Crated successfully", HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<PaymentDto> get(Integer id) {
        var payment = this.paymentRepository.findByIdAndDeletedAtIsNull(id)
                .orElse(new Payment());
        return new ResponseEntity<>(modelMapper.map(payment, PaymentDto.class), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> update(PaymentDto dto, Integer id) {
        var payment = this.paymentRepository.findByIdAndDeletedAtIsNull(id)
                .orElse(new Payment());
        var updateCart = this.change.update(dto, payment);
        this.paymentRepository.save(updateCart);
        return new ResponseEntity<>("Updated successfully", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> delete(Integer id) {
        var payment = this.paymentRepository.findByIdAndDeletedAtIsNull(id)
                .orElse(new Payment());
        payment.setDeletedAt(LocalDateTime.now());
        this.paymentRepository.delete(payment);
        return new ResponseEntity<>("Deleted successfully", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<PaymentDto>> getAll() {
        List<Payment> paymentList = this.paymentRepository.findAll();
        if (paymentList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(Arrays.asList(modelMapper.map(paymentList, PaymentDto[].class)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Page<PaymentDto>> getPage(Integer page, Integer size) {
        var paymentPage = this.paymentRepository.findAllByDeletedAtIsNull(PageRequest.of(page, size));
        if (paymentPage.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(paymentPage.map(payment -> modelMapper.map(payment, PaymentDto.class)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<PaymentDto>> getAllByCustomerId(Integer customerId) {
        List<Payment> paymentList = this.paymentRepository.findByCustomerIdAndCreatedAtIsNull(customerId).stream().toList();
        if (paymentList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(Arrays.asList(modelMapper.map(paymentList, PaymentDto[].class)), HttpStatus.OK);
    }

    public ResponseEntity<PaymentDto> getAllWithOrders(Integer id) {
        var payment = this.paymentRepository.findByIdAndDeletedAtIsNull(id).orElse(new Payment());
        var dto = modelMapper.map(payment, PaymentDto.class);
        dto.setOrders(this.orderClient.getAllByPaymentId(id).getBody());
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
