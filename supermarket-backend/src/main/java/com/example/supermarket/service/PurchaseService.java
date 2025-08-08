package com.example.supermarket.service;

import com.example.supermarket.dto.PurchaseRequest;
import com.example.supermarket.model.*;
import com.example.supermarket.repository.ProductRepository;
import com.example.supermarket.repository.PurchaseOrderRepository;
import com.example.supermarket.repository.SupplierRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class PurchaseService {
    private final SupplierRepository supplierRepository;
    private final ProductRepository productRepository;
    private final PurchaseOrderRepository purchaseOrderRepository;

    public PurchaseService(SupplierRepository supplierRepository, ProductRepository productRepository, PurchaseOrderRepository purchaseOrderRepository) {
        this.supplierRepository = supplierRepository;
        this.productRepository = productRepository;
        this.purchaseOrderRepository = purchaseOrderRepository;
    }

    @Transactional
    public PurchaseOrder create(PurchaseRequest request) {
        Supplier supplier = supplierRepository.findById(request.getSupplierId()).orElseThrow();
        PurchaseOrder order = new PurchaseOrder();
        order.setSupplier(supplier);

        BigDecimal total = BigDecimal.ZERO;
        for (PurchaseRequest.Item it : request.getItems()) {
            Product product = productRepository.findById(it.getProductId()).orElseThrow();
            PurchaseItem item = new PurchaseItem();
            item.setOrder(order);
            item.setProduct(product);
            item.setQuantity(it.getQuantity());
            item.setUnitPrice(it.getUnitPrice());
            BigDecimal line = it.getUnitPrice().multiply(BigDecimal.valueOf(it.getQuantity()));
            item.setLineTotal(line);
            order.getItems().add(item);

            product.setStock(product.getStock() + it.getQuantity());
            productRepository.save(product);

            total = total.add(line);
        }
        order.setTotalAmount(total);
        return purchaseOrderRepository.save(order);
    }
}