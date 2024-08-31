package com.microservice.stockmicroservice.domain.spi;

import java.util.List;

public interface ICategoryProductPersistencePort {
    void createRelationsCategories(List<Long> categoryIds, Long productId);
}
