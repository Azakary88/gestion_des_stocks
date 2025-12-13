package com.example.inventory.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

import com.example.inventory.entity.StockMovement;
import com.example.inventory.entity.Product;
import com.example.inventory.repository.StockMovementRepository;
import com.example.inventory.repository.ProductRepository;

@Service
@Transactional
public class StockMovementService {

    private final StockMovementRepository repo;
    private final ProductRepository productRepo;

    public StockMovementService(
            StockMovementRepository repo,
            ProductRepository productRepo
    ){
        this.repo = repo;
        this.productRepo = productRepo;
    }

    public List<StockMovement> list() {
        return repo.findAll();
    }

    public List<StockMovement> listByProduct(Long productId) {
        return repo.findByProductId(productId);
    }

    public StockMovement record(StockMovement m) {

        Product p = productRepo.findById(m.getProduct().getId())
                .orElseThrow(() -> new RuntimeException("Produit introuvable"));

        //  Sécurité 1 : quantité valide
        if (m.getQuantity() <= 0) {
            throw new RuntimeException("Quantité invalide");
        }

        //  Sécurité 2 : type valide
        String type = m.getType().toUpperCase();

        if ("IN".equals(type)) {

            p.setStockQuantity(
                p.getStockQuantity() + m.getQuantity()
            );

        } else if ("OUT".equals(type)) {

            //  Sécurité 3 : stock suffisant
            if (p.getStockQuantity() < m.getQuantity()) {
                throw new RuntimeException("Stock insuffisant");
            }

            //  Sécurité 4 : pas de sortie sans prix
            if (p.getPrice() <= 0) {
                throw new RuntimeException("Produit sans prix, sortie interdite");
            }

            p.setStockQuantity(
                p.getStockQuantity() - m.getQuantity()
            );

        } else {
            throw new RuntimeException("Type de mouvement invalide (IN / OUT)");
        }

        productRepo.save(p);
        return repo.save(m);
    }
}
