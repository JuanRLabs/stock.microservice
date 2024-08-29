package com.microservice.stockmicroservice.domain.api.usecase;

import com.microservice.stockmicroservice.domain.spi.category.ICategoryPersistencePort;
import com.microservice.stockmicroservice.domain.api.ICategoryServicePort;
import com.microservice.stockmicroservice.domain.model.Category;
import com.microservice.stockmicroservice.domain.util.Pagination.PageableRequest;
import com.microservice.stockmicroservice.domain.util.Pagination.Paginated;

public class CategoryUseCase implements ICategoryServicePort {

    private final ICategoryPersistencePort categoryPersistencePort;

    public CategoryUseCase(ICategoryPersistencePort categoryCreatePersistencePort){
        this.categoryPersistencePort = categoryCreatePersistencePort;
    }

    @Override
    public void create(Category category) {
        categoryPersistencePort.create(category);
    }

    @Override
    public Paginated<Category> listAllCategories(PageableRequest pageableRequest){
        return categoryPersistencePort.listAllCategories(pageableRequest);
    }
}


