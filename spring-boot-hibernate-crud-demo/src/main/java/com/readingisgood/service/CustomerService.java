package com.readingisgood.service;

import com.readingisgood.dto.CustomerDTO;
import com.readingisgood.exception.RecordNotFoundException;
import com.readingisgood.model.CustomerEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface CustomerService {

     List<CustomerEntity> getAllCustomers();

     CustomerEntity getCustomerById(Long id)  throws RecordNotFoundException;

     CustomerEntity createCustomer(@RequestBody CustomerDTO customerDTO);

     void deleteCustomerById(Long id) throws RecordNotFoundException;
}
