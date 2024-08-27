package com.microservice.stockmicroservice.domain.api;

import com.microservice.stockmicroservice.domain.model.Category;
import org.springframework.data.domain.Page;

public interface ICategoryServicePort {
    void create(Category category);
    Page<Category> listAllCategories(int page, int size, String sort);
}
