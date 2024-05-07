package com.example.productservice.mapper;

import com.example.productservice.dto.ProductRequest;
import com.example.productservice.dto.ProductResponse;
import com.example.productservice.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    Product toModel(ProductRequest request);
    ProductRequest toDto(Product product);
    ProductResponse toResponse(Product product);

    void map(@MappingTarget Product product, ProductRequest request);
}
