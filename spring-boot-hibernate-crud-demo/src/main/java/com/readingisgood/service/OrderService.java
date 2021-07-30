package com.readingisgood.service;

import com.readingisgood.dto.OrderCreateRequest;
import com.readingisgood.dto.StatisticOrderDTO;
import com.readingisgood.exception.OutOfStockException;
import com.readingisgood.exception.RecordNotFoundException;
import com.readingisgood.model.OrderEntity;

import javax.persistence.EntityExistsException;
import java.util.Date;
import java.util.List;

public interface OrderService {

    OrderEntity createOrder(OrderCreateRequest orderCreateRequest) throws EntityExistsException, OutOfStockException;

    OrderEntity getOrderById(Long id) throws RecordNotFoundException;

    List<OrderEntity> findAllByOrderDateLessThanEqualAndShippedDateGreaterThanEqual(Date orderDate, Date shippedDate);

    long countById(Long id);

    List<StatisticOrderDTO> getStatisticDTOList();

}
