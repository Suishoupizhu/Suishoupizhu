package com.example.supermarket.repository;

import com.example.supermarket.model.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Long> {
    List<PurchaseOrder> findByCreatedAtBetween(LocalDateTime from, LocalDateTime to);
}