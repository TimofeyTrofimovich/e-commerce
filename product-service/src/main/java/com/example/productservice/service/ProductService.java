package com.example.productservice.service;

import com.example.productservice.dto.ProductRequest;
import com.example.productservice.dto.ProductResponse;
import com.example.productservice.mapper.ProductMapper;
import com.example.productservice.model.Product;
import com.example.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductMapper mapper;
    private final ProductRepository repository;
    public void createProduct(ProductRequest request) {
        Product product = mapper.toModel(request);
        repository.save(product);
        log.info("Product {} is saved", product.getId());
    }

    public List<ProductResponse> getAllProducts() {
        List<Product> all = repository.findAll();
        return all.stream().map(mapper::toResponse).toList();
    }

    public Optional<ProductResponse> getProduct(Integer id) {
        return repository.findById(id).map(mapper::toResponse);
    }

    public Optional<ProductResponse> updateProduct(Integer id, ProductRequest productRequest) {
        return repository.findById(id)
                .map(product -> {
                    mapper.map(product, productRequest);
                    return product;
                }).map(repository::save)

                .map(mapper::toResponse)                ;
    }

    public boolean deleteProduct(Integer id) {
        return repository.findById(id)
                .map(product -> {
                    repository.deleteById(id);
                    return true;
                })
                .orElse(false);
    }

    public boolean productExists(Integer id) {
        return repository.findById(id).isPresent();
    }
}