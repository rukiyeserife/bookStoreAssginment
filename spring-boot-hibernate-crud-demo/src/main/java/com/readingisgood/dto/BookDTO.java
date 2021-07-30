package com.readingisgood.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookDTO {

    private String bookName;

    private String bookVendor;

    private String bookDescription;

    private Long quantityInStock;

    private double buyPrice;

}
