package com.example.supermarket.controller;

import com.example.supermarket.model.Product;
import com.example.supermarket.repository.ProductRepository;
import com.example.supermarket.repository.SaleOrderRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/reports")
public class ReportController {
    private final ProductRepository productRepository;
    private final SaleOrderRepository saleOrderRepository;

    public ReportController(ProductRepository productRepository, SaleOrderRepository saleOrderRepository) {
        this.productRepository = productRepository;
        this.saleOrderRepository = saleOrderRepository;
    }

    @GetMapping("/stock")
    public ResponseEntity<List<Product>> stock() {
        return ResponseEntity.ok(productRepository.findAll());
    }

    @GetMapping("/sales-summary")
    public ResponseEntity<Map<String, Object>> salesSummary(@RequestParam String from, @RequestParam String to) {
        var orders = saleOrderRepository.findByCreatedAtBetween(LocalDateTime.parse(from), LocalDateTime.parse(to));
        BigDecimal total = orders.stream().map(o -> o.getTotalAmount()).reduce(BigDecimal.ZERO, BigDecimal::add);
        Map<String, Object> body = new HashMap<>();
        body.put("count", orders.size());
        body.put("totalAmount", total);
        return ResponseEntity.ok(body);
    }
}