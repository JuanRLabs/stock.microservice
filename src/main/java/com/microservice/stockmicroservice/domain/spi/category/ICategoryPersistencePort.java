package com.microservice.stockmicroservice.domain.spi.category;

import com.microservice.stockmicroservice.domain.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ICategoryPersistencePort {
    void create(Category category);
    Page<Category> listAllCategories(Pageable pageable);
}
