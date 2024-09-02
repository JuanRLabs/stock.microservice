package com.microservice.stockmicroservice.domain.api;

import com.microservice.stockmicroservice.domain.model.Category;


public interface ICategoryServicePort {
    void create(Category category);
}
