package com.example.supermarket.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class SaleRequest {
    private Long customerId;
    private List<Item> items;

    @Data
    public static class Item {
        private Long productId;
        private Integer quantity;
        private BigDecimal unitPrice;
    }
}