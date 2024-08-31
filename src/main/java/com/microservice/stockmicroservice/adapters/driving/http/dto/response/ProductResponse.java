package com.microservice.stockmicroservice.adapters.driving.http.dto.response;

import com.microservice.stockmicroservice.domain.model.Brand;
import com.microservice.stockmicroservice.domain.model.Category;

import java.math.BigDecimal;
import java.util.Set;

public class ProductResponse {
    private Long id;
    private String name;
    private String description;
    private Long quantity;
    private BigDecimal price;
    private BrandResponse brand;
    private Set<Category> categories;

}
