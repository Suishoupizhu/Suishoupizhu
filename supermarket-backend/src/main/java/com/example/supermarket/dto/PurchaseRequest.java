package com.example.supermarket.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class PurchaseRequest {
    private Long supplierId;
    private List<Item> items;

    @Data
    public static class Item {
        private Long productId;
        private Integer quantity;
        private BigDecimal unitPrice;
    }
}