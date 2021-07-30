package com.readingisgood.controller;

import com.readingisgood.exception.OutOfStockException;
import com.readingisgood.exception.RecordNotFoundException;
import com.readingisgood.model.OrderEntity;
import com.readingisgood.service.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.readingisgood.dto.OrderCreateRequest;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {


    @Autowired
    private OrderServiceImpl orderService;

    @PostMapping
    public ResponseEntity<OrderEntity> createOrder(@RequestBody OrderCreateRequest orderCreateRequest) throws OutOfStockException {
        OrderEntity order = orderService.createOrder(orderCreateRequest);
        return new ResponseEntity<OrderEntity>(order, new HttpHeaders(), HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<OrderEntity> getOrderById(@PathVariable("id") Long id)
            throws RecordNotFoundException {
        OrderEntity entity = orderService.getOrderById(id);
        return new ResponseEntity<OrderEntity>(entity, new HttpHeaders(), HttpStatus.OK);
    }


    @GetMapping("/dateRangeQuery")
    public List<OrderEntity>  date(@RequestParam("orderDate")
                     @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date orderDate,@RequestParam("shippedDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date shippedDate) {

       List<OrderEntity> entityList = orderService.findAllByOrderDateLessThanEqualAndShippedDateGreaterThanEqual(orderDate,shippedDate);
       return entityList;
    }

}