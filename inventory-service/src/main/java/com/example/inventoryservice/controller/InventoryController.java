package com.example.inventoryservice.controller;

import com.example.inventoryservice.dto.InventoryResponse;
import com.example.inventoryservice.dto.ProductRequest;
import com.example.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;


    @GetMapping("/{productId}")
    public ResponseEntity<InventoryResponse> getProductStock(@PathVariable(name = "productId") Long productId) {
        return inventoryService.getProductStock(productId)
                .map(productStock -> ok().body(productStock))
                .orElseGet(() -> notFound().build());
    }

    @PutMapping("/{productId}/inc")
    public ResponseEntity<Void> increaseProductStock(@PathVariable(name = "productId") Long productId) {
        return inventoryService.increaseProductStock(productId)
                ? ok().build()
                : notFound().build();
    }

    @PutMapping("/{productId}/dec")
    public ResponseEntity<Void> decreaseProductStock(@PathVariable(name = "productId") Long productId) {
        return inventoryService.decreaseProductStock(productId)
                ? ok().build()
                : notFound().build();
    }


}
