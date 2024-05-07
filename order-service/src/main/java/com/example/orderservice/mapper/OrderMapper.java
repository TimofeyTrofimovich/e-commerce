package com.example.orderservice.mapper;

import com.example.orderservice.dto.OrderRequest;
import com.example.orderservice.model.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    Order toModel(OrderRequest request);
    OrderRequest toDto(Order order);
}
