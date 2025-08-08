package com.example.supermarket.service;

import com.example.supermarket.dto.SaleRequest;
import com.example.supermarket.model.*;
import com.example.supermarket.repository.CustomerRepository;
import com.example.supermarket.repository.ProductRepository;
import com.example.supermarket.repository.SaleOrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class SaleService {
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final SaleOrderRepository saleOrderRepository;

    public SaleService(CustomerRepository customerRepository, ProductRepository productRepository, SaleOrderRepository saleOrderRepository) {
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
        this.saleOrderRepository = saleOrderRepository;
    }

    @Transactional
    public SaleOrder create(SaleRequest request) {
        Customer customer = customerRepository.findById(request.getCustomerId()).orElseThrow();
        SaleOrder order = new SaleOrder();
        order.setCustomer(customer);

        BigDecimal total = BigDecimal.ZERO;
        for (SaleRequest.Item it : request.getItems()) {
            Product product = productRepository.findById(it.getProductId()).orElseThrow();
            if (product.getStock() < it.getQuantity()) {
                throw new IllegalStateException("库存不足: " + product.getName());
            }
            SaleItem item = new SaleItem();
            item.setOrder(order);
            item.setProduct(product);
            item.setQuantity(it.getQuantity());
            item.setUnitPrice(it.getUnitPrice());
            BigDecimal line = it.getUnitPrice().multiply(BigDecimal.valueOf(it.getQuantity()));
            item.setLineTotal(line);
            order.getItems().add(item);

            product.setStock(product.getStock() - it.getQuantity());
            productRepository.save(product);

            total = total.add(line);
        }
        order.setTotalAmount(total);
        return saleOrderRepository.save(order);
    }
}