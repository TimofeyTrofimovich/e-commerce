package com.example.inventoryservice.dto;

import java.math.BigDecimal;

public record ProductResponse(Integer id, String name, String description, BigDecimal price) {

}
