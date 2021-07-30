package com.readingisgood.controller;

import com.readingisgood.dto.StatisticOrderDTO;
import com.readingisgood.exception.RecordNotFoundException;
import com.readingisgood.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/statistcs")
public class StatisticsController {

    @Autowired
    private OrderService orderService;


    @GetMapping
    public ResponseEntity<List<StatisticOrderDTO>> getOrderById(@PathVariable("id") Long id){
        List<StatisticOrderDTO> statisticOrderDTOList = orderService.getStatisticDTOList();
        return new ResponseEntity<List<StatisticOrderDTO>>(statisticOrderDTOList, new HttpHeaders(), HttpStatus.OK);
    }




}
