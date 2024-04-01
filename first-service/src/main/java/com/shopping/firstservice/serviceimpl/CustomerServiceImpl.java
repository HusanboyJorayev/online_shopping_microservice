package com.shopping.firstservice.serviceimpl;

import com.shopping.firstservice.clent.CartClient;
import com.shopping.firstservice.clent.PaymentClient;
import com.shopping.firstservice.clent.dto.CartDto;
import com.shopping.firstservice.dto.CustomerDto;
import com.shopping.firstservice.change.ExchangeCustomer;
import com.shopping.firstservice.entity.Customer;
import com.shopping.firstservice.repository.CustomerRepository;
import com.shopping.firstservice.service.CustomerService;
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
public class CustomerServiceImpl implements CustomerService<Integer, CustomerDto> {
    private final CustomerRepository customerRepository;
    private final PaymentClient paymentClient;
    private final ExchangeCustomer customer;
    private final ModelMapper modelMapper;
    private final CartClient cartClient;

    @Override
    public ResponseEntity<String> create(CustomerDto dto) {
        var customer = this.customer.create(dto);
        this.customerRepository.save(customer);
        System.out.println(modelMapper.map(customer, CustomerDto.class));
        return new ResponseEntity<>("Created successfully", HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<CustomerDto> get(Integer id) {
        var customer = this.customerRepository.findByIdAndDeletedAtIsNull(id)
                .orElse(null);
        return new ResponseEntity<>(modelMapper.map(customer, CustomerDto.class), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> update(CustomerDto dto, Integer id) {
        var customer = this.customerRepository.findByIdAndDeletedAtIsNull(id)
                .orElse(new Customer());
        var newCustomer = this.customer.update(customer, dto);
        this.customerRepository.save(newCustomer);
        System.out.println(modelMapper.map(newCustomer, CustomerDto.class));
        return new ResponseEntity<>("Updated successfully", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> delete(Integer id) {
        var customer = this.customerRepository.findByIdAndDeletedAtIsNull(id)
                .orElse(new Customer());
        customer.setDeletedAt(LocalDateTime.now());
        this.customerRepository.delete(customer);
        return new ResponseEntity<>("Deleted successfully", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<CustomerDto>> getAll() {
        List<Customer> customerList = this.customerRepository.findAll();
        if (customerList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(Arrays.asList(modelMapper.map(customerList, CustomerDto[].class))
                , HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Page<CustomerDto>> getPage(Integer page, Integer size) {
        Page<Customer> customerPage = this.customerRepository.findAllByDeletedAtIsNull(PageRequest.of(page, size));
        if (customerPage.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(customerPage.map(customer -> modelMapper.map(customer, CustomerDto.class)),
                HttpStatus.OK);
    }

    public ResponseEntity<CustomerDto> getAllWithCart(Integer id) {
        var customer = this.customerRepository.findByIdAndDeletedAtIsNull(id).orElse(new Customer());
        CustomerDto dto = modelMapper.map(customer, CustomerDto.class);
        var allCustomer = this.cartClient.getAllCartByCustomerId(id).getBody();
        dto.setCarts(allCustomer);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    public ResponseEntity<CustomerDto> getAllWithPayment(Integer id) {
        var customer = this.customerRepository.findByIdAndDeletedAtIsNull(id).orElse(new Customer());
        CustomerDto dto = modelMapper.map(customer, CustomerDto.class);
        var allCustomer = this.paymentClient.getAllByCustomerId(id).getBody();
        dto.setPayments(allCustomer);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
