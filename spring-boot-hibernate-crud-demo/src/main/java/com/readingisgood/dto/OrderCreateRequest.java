package com.readingisgood.dto;

import com.readingisgood.model.BookEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

import java.util.Map;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderCreateRequest {

    private Date orderDate;

    private Date shippedDate;

    Map<Long,Long> bookOrders;
}
