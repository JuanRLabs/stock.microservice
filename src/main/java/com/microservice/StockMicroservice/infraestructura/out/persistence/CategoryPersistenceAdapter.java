package com.microservice.StockMicroservice.infraestructura.out.persistence;

import com.microservice.StockMicroservice.aplication.port.out.ISaveCategoryPort;
import com.microservice.StockMicroservice.aplication.port.out.IValidateCategoryExist;
import com.microservice.StockMicroservice.domain.CategoryDomain;
import org.springframework.stereotype.Component;

@Component
public class CategoryPersistenceAdapter implements ISaveCategoryPort , IValidateCategoryExist {

    private final CategoryRepository  categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryPersistenceAdapter(CategoryRepository categoryRepository,
                                      CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public CategoryEntity save(CategoryDomain category) {
        return categoryRepository.save(categoryMapper.domainToEntity(category));
    }

    @Override
    public boolean categoryExist(String name) {
        CategoryEntity response = categoryRepository.findByName(name);
        if (response == null)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
}
