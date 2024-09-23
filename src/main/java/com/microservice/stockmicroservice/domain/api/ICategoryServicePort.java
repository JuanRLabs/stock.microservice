package com.microservice.stockmicroservice.domain.api;

import com.microservice.stockmicroservice.domain.model.Category;
import com.microservice.stockmicroservice.domain.util.Pagination.Paginated;
import com.microservice.stockmicroservice.domain.util.Pagination.Sorted;

public interface ICategoryServicePort {
    void create(Category category);
    Paginated<Category> listAllCategories(int page, int size, String sort, Sorted sorted);

}
