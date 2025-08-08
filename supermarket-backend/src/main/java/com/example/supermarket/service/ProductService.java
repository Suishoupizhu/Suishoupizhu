package com.example.supermarket.service;

import com.example.supermarket.model.Product;
import com.example.supermarket.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> list() {
        return productRepository.findAll();
    }

    public Product get(Long id) {
        return productRepository.findById(id).orElseThrow();
    }

    public Product create(Product p) {
        return productRepository.save(p);
    }

    public Product update(Long id, Product p) {
        Product existing = get(id);
        existing.setSku(p.getSku());
        existing.setName(p.getName());
        existing.setDescription(p.getDescription());
        existing.setCategory(p.getCategory());
        existing.setPrice(p.getPrice());
        existing.setStock(p.getStock());
        return productRepository.save(existing);
    }

    public void delete(Long id) {
        productRepository.deleteById(id);
    }
}