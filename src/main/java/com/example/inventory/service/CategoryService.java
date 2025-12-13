package com.example.inventory.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import com.example.inventory.entity.Category;
import com.example.inventory.repository.CategoryRepository;

@Service
@Transactional
public class CategoryService {

    private final CategoryRepository repo;

    public CategoryService(CategoryRepository repo) {
        this.repo = repo;
    }

    public List<Category> list() { return repo.findAll(); }

    public Category get(Long id) { return repo.findById(id).orElse(null); }

    public Category create(Category c) { return repo.save(c); }

    public Category update(Long id, Category c) {
        c.setId(id);
        return repo.save(c);
    }

    public void delete(Long id) { repo.deleteById(id); }
}
