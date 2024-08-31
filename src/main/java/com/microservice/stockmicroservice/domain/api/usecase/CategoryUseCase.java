package com.microservice.stockmicroservice.domain.api.usecase;

import com.microservice.stockmicroservice.adapters.driven.jpa.mysql.exception.CategoryAlreadyExistsException;
import com.microservice.stockmicroservice.domain.exceptions.EmptyFieldException;
import com.microservice.stockmicroservice.domain.exceptions.IllegalArgumentNameException;
import com.microservice.stockmicroservice.domain.spi.category.ICategoryPersistencePort;
import com.microservice.stockmicroservice.domain.api.ICategoryServicePort;
import com.microservice.stockmicroservice.domain.model.Category;
import com.microservice.stockmicroservice.domain.util.DomainConstants;
import com.microservice.stockmicroservice.domain.util.Pagination.PageableRequest;
import com.microservice.stockmicroservice.domain.util.Pagination.Paginated;
import com.microservice.stockmicroservice.domain.util.StringUtilsEmazon;

public class CategoryUseCase implements ICategoryServicePort {

    private final ICategoryPersistencePort categoryPersistencePort;

    public CategoryUseCase(ICategoryPersistencePort categoryCreatePersistencePort){
        this.categoryPersistencePort = categoryCreatePersistencePort;
    }

    @Override
    public void create(Category category) {
        if (StringUtilsEmazon.isEmpty(category.getName())
                || !StringUtilsEmazon.isValidLength(category.getName(), 50)
                || !StringUtilsEmazon.isAlphabetic(category.getName()))
        {
            throw new IllegalArgumentNameException();
        }
        if (StringUtilsEmazon.isEmpty(category.getDescription()))
        {
            throw new IllegalArgumentNameException();
        }if (!categoryPersistencePort.existsByName(category.getName().trim())) {
                Category data = new Category(null, category.getName(), category.getDescription());
            categoryPersistencePort.create(data);
            }else {throw new CategoryAlreadyExistsException();}

        categoryPersistencePort.create(category);
    }

    @Override
    public Paginated<Category> listAllCategories(PageableRequest pageableRequest){
        return categoryPersistencePort.listAllCategories(pageableRequest);
    }
}


