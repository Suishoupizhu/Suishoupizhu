package com.example.supermarket.controller;

import com.example.supermarket.dto.PurchaseRequest;
import com.example.supermarket.model.PurchaseOrder;
import com.example.supermarket.repository.PurchaseOrderRepository;
import com.example.supermarket.service.PurchaseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/purchases")
public class PurchaseController {
    private final PurchaseService purchaseService;
    private final PurchaseOrderRepository purchaseOrderRepository;

    public PurchaseController(PurchaseService purchaseService, PurchaseOrderRepository purchaseOrderRepository) {
        this.purchaseService = purchaseService;
        this.purchaseOrderRepository = purchaseOrderRepository;
    }

    @PostMapping
    public ResponseEntity<PurchaseOrder> create(@RequestBody PurchaseRequest req) {
        return ResponseEntity.ok(purchaseService.create(req));
    }

    @GetMapping
    public ResponseEntity<List<PurchaseOrder>> list(
            @RequestParam(required = false) String from,
            @RequestParam(required = false) String to) {
        if (from != null && to != null) {
            return ResponseEntity.ok(purchaseOrderRepository.findByCreatedAtBetween(LocalDateTime.parse(from), LocalDateTime.parse(to)));
        }
        return ResponseEntity.ok(purchaseOrderRepository.findAll());
    }
}