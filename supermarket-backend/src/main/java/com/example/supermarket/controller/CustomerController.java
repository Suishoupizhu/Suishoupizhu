package com.example.supermarket.controller;

import com.example.supermarket.model.Customer;
import com.example.supermarket.repository.CustomerRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    private final CustomerRepository customerRepository;

    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping
    public ResponseEntity<List<Customer>> list() { return ResponseEntity.ok(customerRepository.findAll()); }

    @PostMapping
    public ResponseEntity<Customer> create(@RequestBody Customer c) { return ResponseEntity.ok(customerRepository.save(c)); }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> update(@PathVariable Long id, @RequestBody Customer c) {
        Customer ex = customerRepository.findById(id).orElseThrow();
        ex.setName(c.getName()); ex.setPhone(c.getPhone()); ex.setAddress(c.getAddress());
        return ResponseEntity.ok(customerRepository.save(ex));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) { customerRepository.deleteById(id); return ResponseEntity.noContent().build(); }
}