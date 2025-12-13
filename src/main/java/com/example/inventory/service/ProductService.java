// src/main/java/com/example/inventory/service/ProductService.java
package com.example.inventory.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import com.example.inventory.entity.Product;
import com.example.inventory.repository.ProductRepository;

@Service
@Transactional
public class ProductService {
    private final ProductRepository repo;
    public ProductService(ProductRepository repo){ this.repo = repo; }
    public List<Product> list(){ return repo.findAll(); }
    public List<Product> findByCategory(Long categoryId) {
        return repo.findByCategoryId(categoryId);
    }
    public Product get(Long id){ return repo.findById(id).orElse(null); }
    public Product create(Product p){ return repo.save(p); }
    public Product update(Long id, Product p){ p.setId(id); return repo.save(p); }
    public void delete(Long id){ repo.deleteById(id); }
    public double totalStockValue() {
        Double v = repo.totalStockValue();
        return v == null ? 0.0 : v;
    }
}
