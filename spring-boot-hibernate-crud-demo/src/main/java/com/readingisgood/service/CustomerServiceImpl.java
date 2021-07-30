package com.readingisgood.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.readingisgood.dto.CustomerDTO;
import com.readingisgood.exception.DataInvalidException;
import com.readingisgood.exception.RecordNotFoundException;
import com.readingisgood.model.CustomerEntity;
import com.readingisgood.model.OrderEntity;
import com.readingisgood.repository.CustomerRepository;
import com.readingisgood.util.UtilClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository repository;


    @Autowired
    ObjectMapper objectMapper;

    public List<CustomerEntity> getAllCustomers() {
        List<CustomerEntity> customerList = repository.findAll();

        if (customerList.size() > 0) {
            return customerList;
        } else {
            return new ArrayList<CustomerEntity>();
        }
    }

    public CustomerEntity getCustomerById(Long id) throws RecordNotFoundException {
        Optional<CustomerEntity> customer = repository.findById(id);

        if (customer.isPresent()) {
            return customer.get();
        } else {
            throw new RecordNotFoundException("No customer record exist for given id");
        }
    }


    public Set<OrderEntity> getCustomerOrdersById(Long id) throws RecordNotFoundException {
        Optional<CustomerEntity> customer = repository.findById(id);

        if (customer.isPresent()) {
            return customer.get().getOrders();
        } else {
            throw new RecordNotFoundException("No customer record exist for given id");
        }
    }


    public CustomerEntity createCustomer(@RequestBody CustomerDTO customerDTO) {
        CustomerEntity entity = objectMapper.convertValue(customerDTO, CustomerEntity.class);

        if (!isCustomerValid(entity)) {
            throw new DataInvalidException("Some customer fields are incorrect!");
        }
        return    repository.save(entity);
    }

    public void deleteCustomerById(Long id) throws RecordNotFoundException {
        Optional<CustomerEntity> customer = repository.findById(id);

        if (customer.isPresent()) {
            repository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No customer record exist for given id");
        }
    }

    private boolean isCustomerValid(CustomerEntity customerEntity) {
        String email = customerEntity.getEmail();
        String name = customerEntity.getFirstName();
        String surname = customerEntity.getLastName();
        return name != null && !name.isEmpty() && surname != null && !surname.isEmpty() && UtilClass.validate(email);
    }


}