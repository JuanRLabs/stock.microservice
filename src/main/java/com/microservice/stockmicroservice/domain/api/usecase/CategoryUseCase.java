package com.microservice.stockmicroservice.domain.api.usecase;

import com.microservice.stockmicroservice.domain.exceptions.CategoryAlreadyExistsException;
import com.microservice.stockmicroservice.domain.exceptions.IllegalArgumentDescriptionException;
import com.microservice.stockmicroservice.domain.exceptions.IllegalArgumentNameException;
import com.microservice.stockmicroservice.domain.spi.category.ICategoryPersistencePort;
import com.microservice.stockmicroservice.domain.api.ICategoryServicePort;
import com.microservice.stockmicroservice.domain.model.Category;
import com.microservice.stockmicroservice.domain.utilityClass.InputValidate;

public class CategoryUseCase implements ICategoryServicePort {

    private final ICategoryPersistencePort categoryPersistencePort;

    public CategoryUseCase(ICategoryPersistencePort categoryCreatePersistencePort){
        this.categoryPersistencePort = categoryCreatePersistencePort;
    }

    @Override
    public void create(Category category) {
        if (InputValidate.isEmpty(category.getName())
                || !InputValidate.isValidLength(category.getName(), 50)
                || !InputValidate.isAlphabetic(category.getName()))
        {
            throw new IllegalArgumentNameException();
        }
        if (InputValidate.isEmpty(category.getDescription()))
        {
            throw new IllegalArgumentDescriptionException();
        }
        if (!categoryPersistencePort.existsByName(category.getName().trim()))
        {
            Category data = new Category(null, category.getName(), category.getDescription());
            categoryPersistencePort.create(data);
        }else {throw new CategoryAlreadyExistsException();}
    }

}


