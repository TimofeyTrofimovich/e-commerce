package com.example.inventoryservice.service;

import com.example.inventoryservice.dto.InventoryResponse;
import com.example.inventoryservice.mapper.InventoryMapper;
import com.example.inventoryservice.model.ProductStock;
import com.example.inventoryservice.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryService {

    private final InventoryRepository inventoryRepository;
    private final InventoryMapper inventoryMapper;
    private final WebClient.Builder webClientBuilder;

    public Optional<InventoryResponse> getProductStock(Long id) {
        return inventoryRepository.findByProductId(id).map(inventoryMapper::toDto);
    }

    public boolean increaseProductStock(Long id) {
        Optional<ProductStock> stockOptional = inventoryRepository.findById(id);
        if (stockOptional.isPresent()) {
            ProductStock productStock = stockOptional.get();
            productStock.setStock(productStock.getStock() + 1);
            inventoryRepository.save(productStock);
        }
        else {
            if (checkProductId(id)) {
                inventoryRepository.save(new ProductStock(id, 1));
            }
            else return false;
        }
        return true;
    }

    private boolean checkProductId(Long id) {
        return Boolean.TRUE.equals(webClientBuilder.build().get()
                .uri("http://product-service/api/product/exists/{id}", id)
                .retrieve()
                .bodyToMono(Boolean.class)
                .block());
    }

    public boolean decreaseProductStock(Long id) {
        Optional<ProductStock> stockOptional = inventoryRepository.findById(id);
        stockOptional.ifPresent(productStock -> {
            Integer stock = productStock.getStock();
            if (stock > 0) {
                productStock.setStock(productStock.getStock() - 1);
                inventoryRepository.save(productStock);
            }
        });
        return stockOptional.isPresent() && stockOptional.get().getStock() > 0;
    }
}
