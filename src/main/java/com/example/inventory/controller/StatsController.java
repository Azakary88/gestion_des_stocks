// src/main/java/com/example/inventory/controller/StatsController.java
package com.example.inventory.controller;

import org.springframework.web.bind.annotation.*;
import java.util.Map;
import com.example.inventory.service.StatsService;

@RestController
@RequestMapping("/api/stats")
public class StatsController {

    private final StatsService service;

    public StatsController(StatsService service) {
        this.service = service;
    }

    @GetMapping
    public Map<String, Object> dashboard() {
        return Map.of(
            "totalProducts", service.countProducts()
        );
    }
}
