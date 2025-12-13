// src/main/java/com/example/inventory/service/StatsService.java
package com.example.inventory.service;

import org.springframework.stereotype.Service;
import com.example.inventory.repository.ProductRepository;

@Service
public class StatsService {

    private final ProductRepository productRepo;

    public StatsService(ProductRepository productRepo) {
        this.productRepo = productRepo;
    }

    public long countProducts() { return productRepo.count(); }
}
