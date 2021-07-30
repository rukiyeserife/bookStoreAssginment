package com.readingisgood.controller;

import com.readingisgood.dto.CustomerDTO;
import com.readingisgood.exception.RecordNotFoundException;
import com.readingisgood.model.CustomerEntity;
import com.readingisgood.model.OrderEntity;
import com.readingisgood.service.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    CustomerServiceImpl service;

    @GetMapping
    public ResponseEntity<List<CustomerEntity>> getAllCustomers() {
        List<CustomerEntity> list = service.getAllCustomers();

        return new ResponseEntity<List<CustomerEntity>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/getOrders/{id}")
    public ResponseEntity<Set<OrderEntity>> getCustomerOrders(@PathVariable("id") Long id) throws RecordNotFoundException {
        Set<OrderEntity> orders = service.getCustomerOrdersById(id);

        return new ResponseEntity<Set<OrderEntity>>(orders, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerEntity> getCustomerById(@PathVariable("id") Long id)
            throws RecordNotFoundException {
        CustomerEntity entity = service.getCustomerById(id);

        return new ResponseEntity<CustomerEntity>(entity, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CustomerEntity> createCustomer(@RequestBody CustomerDTO customerDTO)
            throws RecordNotFoundException {
        CustomerEntity customer = service.createCustomer(customerDTO);
        return new ResponseEntity<CustomerEntity>(customer, new HttpHeaders(), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteCustomerById(@PathVariable("id") Long id)
            throws RecordNotFoundException {
        service.deleteCustomerById(id);
        return HttpStatus.FORBIDDEN;
    }

}