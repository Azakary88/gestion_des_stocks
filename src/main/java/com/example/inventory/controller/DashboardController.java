package com.example.inventory.controller;

import com.example.inventory.entity.Product;
import com.example.inventory.repository.ProductRepository;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    private final ProductRepository productRepo;

    public DashboardController(ProductRepository productRepo) {
        this.productRepo = productRepo;
    }

    @GetMapping
    public Map<String, Object> getDashboard() {
        List<Product> products = productRepo.findAll();

        int totalStock = products.stream().mapToInt(Product::getStockQuantity).sum();
        double totalValue = products.stream()
                .mapToDouble(p -> p.getStockQuantity() * 1.0) 
                .sum();

        List<Product> top5 = products.stream()
                .sorted(Comparator.comparingInt(Product::getStockQuantity).reversed())
                .limit(5)
                .collect(Collectors.toList());

        List<Product> ruptures = products.stream()
                .filter(p -> p.getStockQuantity() <= 0)
                .collect(Collectors.toList());

        Map<String, Object> map = new HashMap<>();
        map.put("totalStock", totalStock);
        map.put("totalValue", totalValue);
        map.put("top5", top5);
        map.put("ruptures", ruptures);

        return map;
    }
}
