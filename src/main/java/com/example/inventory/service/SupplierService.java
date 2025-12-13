package com.example.inventory.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import com.example.inventory.entity.Supplier;
import com.example.inventory.repository.SupplierRepository;



@Service
@Transactional
public class SupplierService {

    private final SupplierRepository repo;

    public SupplierService(SupplierRepository repo){
        this.repo = repo;
    }

    public List<Supplier> list(){ return repo.findAll(); }
    public Supplier get(Long id){ return repo.findById(id).orElse(null); }
    public Supplier create(Supplier s){ return repo.save(s); }
    public Supplier update(Long id, Supplier s){ s.setId(id); return repo.save(s); }
    public void delete(Long id){ repo.deleteById(id); }
}
