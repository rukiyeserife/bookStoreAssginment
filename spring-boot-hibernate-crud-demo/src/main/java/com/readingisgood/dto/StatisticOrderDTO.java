package com.readingisgood.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StatisticOrderDTO {

    private String month;
    private int totalOrderCount;
    private int totalBookCount;
    private double totalPurchasedAmount;

}
