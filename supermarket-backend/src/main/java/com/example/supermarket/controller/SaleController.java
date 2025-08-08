package com.example.supermarket.controller;

import com.example.supermarket.dto.SaleRequest;
import com.example.supermarket.model.SaleOrder;
import com.example.supermarket.repository.SaleOrderRepository;
import com.example.supermarket.service.SaleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/sales")
public class SaleController {
    private final SaleService saleService;
    private final SaleOrderRepository saleOrderRepository;

    public SaleController(SaleService saleService, SaleOrderRepository saleOrderRepository) {
        this.saleService = saleService;
        this.saleOrderRepository = saleOrderRepository;
    }

    @PostMapping
    public ResponseEntity<SaleOrder> create(@RequestBody SaleRequest req) {
        return ResponseEntity.ok(saleService.create(req));
    }

    @GetMapping
    public ResponseEntity<List<SaleOrder>> list(
            @RequestParam(required = false) String from,
            @RequestParam(required = false) String to) {
        if (from != null && to != null) {
            return ResponseEntity.ok(saleOrderRepository.findByCreatedAtBetween(LocalDateTime.parse(from), LocalDateTime.parse(to)));
        }
        return ResponseEntity.ok(saleOrderRepository.findAll());
    }
}