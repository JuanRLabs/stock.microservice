package com.microservice.stockmicroservice.domain.api;

import com.microservice.stockmicroservice.domain.model.Category;
import com.microservice.stockmicroservice.domain.util.Pagination.PageableRequest;
import com.microservice.stockmicroservice.domain.util.Pagination.Paginated;

public interface ICategoryServicePort {
    void create(Category category);
    Paginated<Category> listAllCategories(PageableRequest pageableRequest);

}
