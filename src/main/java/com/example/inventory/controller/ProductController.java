// src/main/java/com/example/inventory/controller/ProductController.java
package com.example.inventory.controller;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.example.inventory.entity.Product;
import com.example.inventory.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService service;
    public ProductController(ProductService service){ this.service = service; }
    @GetMapping public List<Product> list(){ return service.list(); }
    @GetMapping("{id}") public Product get(@PathVariable Long id){ return service.get(id); }
    @GetMapping("/by-category/{catId}")
    public List<Product> getByCategory(@PathVariable Long catId) {
        return service.findByCategory(catId);
    }
    @PostMapping public Product create(@RequestBody Product p){ return service.create(p); }
    @PutMapping("{id}") public Product update(@PathVariable Long id, @RequestBody Product p){ return service.update(id,p); }
    @DeleteMapping("{id}") public void delete(@PathVariable Long id){ service.delete(id); }
}
