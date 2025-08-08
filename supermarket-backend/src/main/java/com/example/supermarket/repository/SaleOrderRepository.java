package com.example.supermarket.repository;

import com.example.supermarket.model.SaleOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface SaleOrderRepository extends JpaRepository<SaleOrder, Long> {
    List<SaleOrder> findByCreatedAtBetween(LocalDateTime from, LocalDateTime to);
}