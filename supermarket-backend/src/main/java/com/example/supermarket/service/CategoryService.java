package com.example.supermarket.service;

import com.example.supermarket.model.Category;
import com.example.supermarket.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> list() {
        return categoryRepository.findAll();
    }

    public Category get(Long id) {
        return categoryRepository.findById(id).orElseThrow();
    }

    public Category create(Category c) {
        return categoryRepository.save(c);
    }

    public Category update(Long id, Category c) {
        Category existing = get(id);
        existing.setName(c.getName());
        existing.setDescription(c.getDescription());
        return categoryRepository.save(existing);
    }

    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }
}