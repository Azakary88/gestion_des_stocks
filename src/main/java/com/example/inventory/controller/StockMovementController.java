package com.example.inventory.controller;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.example.inventory.entity.StockMovement;
import com.example.inventory.service.StockMovementService;

@RestController
@RequestMapping("/api/movements")
public class StockMovementController {

    private final StockMovementService service;

    public StockMovementController(StockMovementService service) {
        this.service = service;
    }

    @GetMapping
    public List<StockMovement> list() {
        return service.list();
    }

    @GetMapping("/product/{id}")
    public List<StockMovement> listByProduct(@PathVariable Long id) {
        return service.listByProduct(id);
    }

    @PostMapping
    public StockMovement create(@RequestBody StockMovement m) {
        return service.record(m);   // correction : record() au lieu de create()
    }
}
