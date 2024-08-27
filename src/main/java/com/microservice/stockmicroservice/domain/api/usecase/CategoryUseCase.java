package com.microservice.stockmicroservice.domain.api.usecase;

import com.microservice.stockmicroservice.domain.spi.category.ICategoryPersistencePort;
import com.microservice.stockmicroservice.domain.api.ICategoryServicePort;
import com.microservice.stockmicroservice.domain.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

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
    public Page<Category> listAllCategories(int page, int size, String sort){
        Sort.Direction direction = Sort.Direction.ASC;
        if ("desc".equalsIgnoreCase(sort)){
            direction = Sort.Direction.DESC;
        }
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, "name"));
        return categoryPersistencePort.listAllCategories(pageable);
    }
}


