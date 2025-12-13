package com.example.inventory.controller;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.example.inventory.entity.Category;
import com.example.inventory.service.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @GetMapping public List<Category> list() { return service.list(); }

    @GetMapping("{id}") public Category get(@PathVariable Long id) {
        return service.get(id);
    }

    @PostMapping public Category create(@RequestBody Category c) {
        return service.create(c);
    }

    @PutMapping("{id}") public Category update(@PathVariable Long id, @RequestBody Category c) {
        return service.update(id, c);
    }

    @DeleteMapping("{id}") public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
