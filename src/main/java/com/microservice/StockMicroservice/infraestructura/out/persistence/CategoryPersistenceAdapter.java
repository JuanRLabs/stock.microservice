package com.microservice.StockMicroservice.infraestructura.out.persistence;

import com.microservice.StockMicroservice.aplication.port.out.IFindAllCategoryPort;
import com.microservice.StockMicroservice.aplication.port.out.ISaveCategoryPort;
import com.microservice.StockMicroservice.aplication.port.out.IValidateCategoryExist;
import com.microservice.StockMicroservice.domain.CategoryDomain;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CategoryPersistenceAdapter implements ISaveCategoryPort , IValidateCategoryExist, IFindAllCategoryPort {

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

    @Override
    public Page<CategoryDomain> findAllCategories(Pageable pageable) {
         Page<CategoryEntity> list = categoryRepository.findAll(pageable);
         return list.map(c -> CategoryDomain.builder()
                .id(c.getId())
                .name(c.getName())
                .description(c.getDescription())
                .build());
    }
}
