package com.microservice.stockmicroservice.domain.spi.category;

import com.microservice.stockmicroservice.domain.model.Category;

public interface ICategoryPersistencePort {
    void create(Category category);
    boolean existsByName(String name);
}
