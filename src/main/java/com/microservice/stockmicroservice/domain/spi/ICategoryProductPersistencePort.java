package com.microservice.stockmicroservice.domain.spi;

import com.microservice.stockmicroservice.domain.model.Category;

import java.util.List;

public interface ICategoryProductPersistencePort {
    void createRelationsCategories(List<Category> categoryIds, Long productId);
}
