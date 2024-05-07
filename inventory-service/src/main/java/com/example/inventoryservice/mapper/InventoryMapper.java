package com.example.inventoryservice.mapper;

import com.example.inventoryservice.dto.InventoryResponse;
import com.example.inventoryservice.model.ProductStock;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface InventoryMapper {
    ProductStock toModel(InventoryResponse inventoryDto);
    @Mapping(target = "quantity", source= "stock")
    InventoryResponse toDto(ProductStock productStock);

}
