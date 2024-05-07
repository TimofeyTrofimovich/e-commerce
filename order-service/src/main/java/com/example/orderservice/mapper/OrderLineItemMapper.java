package com.example.orderservice.mapper;

import com.example.orderservice.dto.OrderLineItemsDto;
import com.example.orderservice.model.OrderLineItems;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderLineItemMapper {
    OrderLineItems toModel(OrderLineItemsDto orderLineItemsDto);
    OrderLineItemsDto toDto(OrderLineItems orderLineItems);
}
