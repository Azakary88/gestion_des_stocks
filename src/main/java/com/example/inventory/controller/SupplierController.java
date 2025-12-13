package com.example.inventory.controller;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.example.inventory.entity.Supplier;
import com.example.inventory.service.SupplierService;



@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {

    private final SupplierService service;

    public SupplierController(SupplierService service){
        this.service = service;
    }

    @GetMapping public List<Supplier> list(){ return service.list(); }

    @GetMapping("{id}") public Supplier get(@PathVariable Long id){ return service.get(id); }

    @PostMapping public Supplier create(@RequestBody Supplier s){ return service.create(s); }

    @PutMapping("{id}") public Supplier update(@PathVariable Long id, @RequestBody Supplier s){
        return service.update(id, s);
    }

    @DeleteMapping("{id}") public void delete(@PathVariable Long id){
        service.delete(id);
    }
}
