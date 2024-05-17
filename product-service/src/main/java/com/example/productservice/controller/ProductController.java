package com.example.productservice.controller;

import com.example.productservice.dto.ProductRequest;
import com.example.productservice.dto.ProductResponse;
import com.example.productservice.service.ProductService;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sun.misc.Unsafe;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<Void> createProduct(@RequestBody ProductRequest productRequest) {
        productService.createProduct(productRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        return ok().body(productService.getAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProduct(@PathVariable Integer id) {
        return productService.getProduct(id)
                .map(productResponse -> ok().body(productResponse))
                .orElseGet(() -> notFound().build());
    }

    @GetMapping("exists/{id}")
    public boolean productExists(@PathVariable Integer id) {
        return productService.productExists(id);
    }


    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable Integer id, @RequestBody ProductRequest productRequest) {
        return productService.updateProduct(id, productRequest)
                .map(productResponse -> ok().body(productResponse))
                .orElseGet(() -> notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer id) {
        return productService.deleteProduct(id)
                ? noContent().build()
                : notFound().build();
    }
}