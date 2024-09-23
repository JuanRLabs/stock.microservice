package com.microservice.stockmicroservice.adapters.driving.http.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
public class AddProductRequest {
    private String name;
    private String description;
    private Long quantity;
    private BigDecimal price;
    private Long brandId;
    private List<Long> categoriesId;

}
