package com.microservice.stockmicroservice.domain.spi.category;

import com.microservice.stockmicroservice.domain.model.Category;
import com.microservice.stockmicroservice.domain.util.Pagination.PageableRequest;
import com.microservice.stockmicroservice.domain.util.Pagination.Paginated;

public interface ICategoryPersistencePort {

    void create(Category category);
    boolean existsByName(String name);
    Paginated<Category> listAllCategories(PageableRequest pageableRequest);
}
