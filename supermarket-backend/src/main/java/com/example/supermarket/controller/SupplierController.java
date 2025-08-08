package com.example.supermarket.controller;

import com.example.supermarket.model.Supplier;
import com.example.supermarket.repository.SupplierRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {
    private final SupplierRepository supplierRepository;

    public SupplierController(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    @GetMapping
    public ResponseEntity<List<Supplier>> list() { return ResponseEntity.ok(supplierRepository.findAll()); }

    @PostMapping
    public ResponseEntity<Supplier> create(@RequestBody Supplier s) { return ResponseEntity.ok(supplierRepository.save(s)); }

    @PutMapping("/{id}")
    public ResponseEntity<Supplier> update(@PathVariable Long id, @RequestBody Supplier s) {
        Supplier ex = supplierRepository.findById(id).orElseThrow();
        ex.setName(s.getName()); ex.setContact(s.getContact()); ex.setPhone(s.getPhone()); ex.setAddress(s.getAddress());
        return ResponseEntity.ok(supplierRepository.save(ex));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) { supplierRepository.deleteById(id); return ResponseEntity.noContent().build(); }
}