package com.example.inventoryservice.repository;

import com.example.inventoryservice.model.ProductStock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InventoryRepository extends JpaRepository<ProductStock,Long> {
    Optional<ProductStock> findByProductId(Long productId);
}
